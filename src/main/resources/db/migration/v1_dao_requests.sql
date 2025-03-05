-- ================  ABOUT THIS FILE   ================
--  Every DAO's, doesn't use a direct SQL query but use the QueryFactories.
--  Every DAO's request are therefore hidden behind the QueryFactories.
--  So, In this file, all SQL queries related to each DAO will be found in here.

--	================    Event 	================
WITH EventCounts AS (
    SELECT id_event, COUNT(*) AS all_events_by_id
    FROM has_type
    GROUP BY id_event
    ORDER BY id_event
    LIMIT ? OFFSET ?
),
     EventTypes AS (
         SELECT
             e.id AS event_id,
             ety.id AS event_type_id,
             ety.event_type,
             ety.description AS event_type_description,
             ec.id AS event_category_id,
             ec.event_category,
             ec.description AS event_category_description
         FROM event e
                  LEFT JOIN has_type h ON e.id = h.id_event
                  LEFT JOIN events_type ety ON ety.id = h.id_events_type
                  LEFT JOIN events_category ec ON ec.id_event_type = ety.id
     )
SELECT
    e.id AS event_id,
    e.title AS event_title,
    e.description AS event_description,
    e.date_time AS event_date_time,
    e.time_zone AS event_time_zone,
    e.location AS event_location,
    e.location_url AS event_location_url,
    e.image_path AS event_image_path,
    e.status AS event_status,
    e.category AS event_category,
    e.number_of_ticket AS event_number_of_ticket,
    e.max_ticket_per_user AS event_max_ticket_per_user,
    c.created_at AS event_created_at,
    c.updated_at AS event_updated_at,
    u.email AS user_email,
    u.last_name AS user_last_name,
    u.first_name AS user_first_name,
    u.user_role AS user_role,
    u.status AS user_status,
    (SELECT ecy.id from "event" ev INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id) as event_category_id,
    (SELECT ecy.event_category FROM "event" ev INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id) AS event_category,
    COALESCE(
                    JSON_AGG(
                    DISTINCT JSONB_BUILD_OBJECT(
                            'id', ety.event_type_id,
                            'event_type', ety.event_type,
                            'description', ety.event_type_description,
                            'corresponding_categories',
                            (SELECT JSONB_AGG(
                                            DISTINCT JSONB_BUILD_OBJECT(
                                            'id', ec2.id,
                                            'event_category', ec2.event_category,
                                            'description', ec2.description
                                                     )
                                    )
                             FROM events_category ec2
                             WHERE ec2.id_event_type = ety.event_type_id)
                             )
                            ) FILTER (WHERE ety.event_type_id IS NOT NULL),
                    '[]'
    ) AS event_types
FROM event e
         INNER JOIN creates c ON c.id_event = e.id
         INNER JOIN "user" u ON u.email = c.user_email
         LEFT JOIN EventTypes ety ON e.id = ety.event_id
         LEFT JOIN events_category ec ON ec.event_category = e.category AND ec.id_event_type = ety.event_type_id
WHERE e.id IN (SELECT id_event FROM EventCounts)
GROUP BY e.id, c.created_at, c.updated_at, u.email, u.last_name, u.first_name, u.user_role, u.status, e.date_time
ORDER BY e.date_time DESC
LIMIT (SELECT COALESCE(SUM(all_events_by_id), 0) FROM EventCounts)
    OFFSET 0;

--	================	EVENT TYPE	===================
WITH EventTypeCounts AS (
    SELECT id_event_type, COUNT(*) AS all_events_type_by_id
    FROM events_category
    GROUP BY id_event_type
    ORDER BY id_event_type
    LIMIT ? OFFSET ?
),
     EventCategories AS (
         SELECT
             ety.id AS event_type_id,
             ec.id AS event_category_id,
             ec.event_category,
             ec.description AS event_category_description
         FROM events_type ety
                  LEFT JOIN events_category ec ON ec.id_event_type = ety.id
     )
SELECT
    ety.id as event_type_id,
    ety.event_type as event_type,
    ety.description as event_type_desctiption,
    COALESCE(
                    JSON_AGG(
                    DISTINCT JSONB_BUILD_OBJECT(
                            'id', ec.event_type_id,
                            'event_category', ec.event_category,
                            'description', ec.event_category_description
                             )
                            ) FILTER (WHERE ec.event_type_id IS NOT NULL),
                    '[]'
    ) AS corresponding_categories
FROM events_type ety
         LEFT JOIN EventCategories ec ON ety.id = ec.event_type_id
WHERE ety.id IN (SELECT id_event_type FROM EventTypeCounts)
GROUP BY ety.id
LIMIT (SELECT COALESCE(SUM(all_events_type_by_id), 0) FROM EventTypeCounts)
    OFFSET 0;


-- ================	    USER	=========================

WITH UserCounts AS (
    SELECT user_email, COUNT(*) AS all_user_email_by_id
    FROM ticket
    GROUP BY user_email
    ORDER BY user_email
    LIMIT ? OFFSET ?
),
     Top5Categories AS (
         SELECT
             u.email AS user_email,
             ec.id AS event_category_id,
             ec.event_category,
             ec.description AS event_category_description,
             COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket WHERE user_email = user_email), 0) AS percentage
         FROM ticket t
                  RIGHT JOIN "user" u ON t.user_email = u.email
                  LEFT JOIN ticket_price tp ON tp.id = t.id_ticket_price
                  LEFT JOIN event e ON e.id = tp.id_event
                  LEFT JOIN events_category ec ON e.category = ec.event_category
         GROUP BY ec.id, u.email
         ORDER BY percentage  DESC
         LIMIT 5
     )
SELECT
    u.email as user_mail,
    u.profile_img_path as user_profile_img_path,
    u.last_name as user_last_name,
    u.first_name AS user_first_name,
    u.user_role AS user_role,
    U.status AS user_status,
    COALESCE(
                    JSON_AGG(
                    DISTINCT JSONB_BUILD_OBJECT(
                            'id', t5c.event_category_id,
                            'event_category', t5c.event_category,
                            'description', t5c.event_category_description
                             )
                            ) FILTER (WHERE t5c.event_category_id IS NOT NULL),
                    '[]'
    ) AS user_top_5_categories
FROM "user" u
         LEFT JOIN Top5Categories t5c ON u.email = t5c.user_email
WHERE u.email IN (SELECT user_email FROM UserCounts)
GROUP BY u.email
LIMIT (SELECT COALESCE(SUM(all_user_email_by_id), 0) FROM UserCounts)
    OFFSET 0;


--	===================	    MIX (EVENT_USER)	=============================
WITH EventCounts AS (
    SELECT id_event, COUNT(*) AS all_events_by_id
    FROM has_type
    GROUP BY id_event
    ORDER BY id_event
    LIMIT ? OFFSET ?
),
     EventTypes AS (
         SELECT
             e.id AS event_id,
             ety.id AS event_type_id,
             ety.event_type,
             ety.description AS event_type_description,
             ec.id AS event_category_id,
             ec.event_category,
             ec.description AS event_category_description
         FROM event e
                  LEFT JOIN has_type h ON e.id = h.id_event
                  LEFT JOIN events_type ety ON ety.id = h.id_events_type
                  LEFT JOIN events_category ec ON ec.id_event_type = ety.id
     ),
     UserCounts AS (
         SELECT user_email, COUNT(*) AS all_user_email_by_id
         FROM ticket
         GROUP BY user_email
         ORDER BY user_email
         LIMIT 10 OFFSET 0
     ),
     Top5Categories AS (
         SELECT
             u.email AS user_email,
             ec.id AS event_category_id,
             ec.event_category,
             ec.description AS event_category_description,
             COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket WHERE user_email = user_email), 0) AS percentage
         FROM ticket t
                  RIGHT JOIN "user" u ON t.user_email = u.email
                  LEFT JOIN ticket_price tp ON tp.id = t.id_ticket_price
                  LEFT JOIN event e ON e.id = tp.id_event
                  LEFT JOIN events_category ec ON e.category = ec.event_category
         GROUP BY ec.id, u.email
         ORDER BY percentage  DESC
         LIMIT 5
     )
SELECT
    e.id AS event_id,
    e.title AS event_title,
    e.description AS event_description,
    e.date_time AS event_date_time,
    e.time_zone AS event_time_zone,
    e.location AS event_location,
    e.location_url AS event_location_url,
    e.image_path AS event_image_path,
    e.status AS event_status,
    e.category AS event_category,
    e.number_of_ticket AS event_number_of_ticket,
    e.max_ticket_per_user AS event_max_ticket_per_user,
    c.created_at AS event_created_at,
    c.updated_at AS event_updated_at,
    u.email AS user_email,
    u.profile_img_path as user_profile_img_path,
    u.last_name AS user_last_name,
    u.first_name AS user_first_name,
    u.user_role AS user_role,
    u.status AS user_status,
    COALESCE(
                    JSON_AGG(
                    DISTINCT JSONB_BUILD_OBJECT(
                            'id', t5c.event_category_id,
                            'event_category', t5c.event_category,
                            'description', t5c.event_category_description
                             )
                            ) FILTER (WHERE t5c.event_category_id IS NOT NULL),
                    '[]'
    ) AS user_top_5_categories,
    (SELECT ecy.id from "event" ev INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id) AS event_category_id,
    (SELECT ecy.event_category FROM "event" ev INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id) AS event_category,
    COALESCE(
                    JSON_AGG(
                    DISTINCT JSONB_BUILD_OBJECT(
                            'id', ety.event_type_id,
                            'event_type', ety.event_type,
                            'description', ety.event_type_description,
                            'corresponding_categories',
                            (SELECT JSONB_AGG(
                                            DISTINCT JSONB_BUILD_OBJECT(
                                            'id', ec2.id,
                                            'event_category', ec2.event_category,
                                            'description', ec2.description
                                                     )
                                    )
                             FROM events_category ec2
                             WHERE ec2.id_event_type = ety.event_type_id)
                             )
                            ) FILTER (WHERE ety.event_type_id IS NOT NULL),
                    '[]'
    ) AS event_types
FROM event e
         INNER JOIN creates c ON c.id_event = e.id
         INNER JOIN "user" u ON u.email = c.user_email
         LEFT JOIN Top5Categories t5c ON u.email = t5c.user_email
         LEFT JOIN EventTypes ety ON e.id = ety.event_id
         LEFT JOIN events_category ec ON ec.event_category = e.category AND ec.id_event_type = ety.event_type_id
WHERE e.id IN (SELECT id_event FROM EventCounts)
GROUP BY e.id, c.created_at, c.updated_at, u.email, u.last_name, u.first_name, u.user_role, u.status, e.date_time
ORDER BY e.date_time DESC
LIMIT (SELECT COALESCE(SUM(all_events_by_id), 0) FROM EventCounts)
    OFFSET 0;