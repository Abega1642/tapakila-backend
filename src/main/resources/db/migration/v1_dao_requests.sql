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
    u.is_active AS user_status,
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
GROUP BY e.id, c.created_at, c.updated_at, u.email, u.last_name, u.first_name, u.user_role, u.is_active, e.date_time
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

--	===============	TICKET_PRICE	===============
WITH TicketType AS (
	SELECT
		tt.id AS ticket_type_id,
		tt.ticket_type AS ticket_type,
		tt.img_path AS ticket_type_img_path,
		tt.description AS ticket_type_description
	FROM tickets_type tt
)
SELECT 
	tp.id as ticket_price_id,
	tp.price as ticket_price,
	tp.currency	as ticket_price_currency,
	tp.created_at as ticket_price_created_at,
	tp.max_number as ticket_price_max_number,
	tp.id_event as associated_event_id,
	COALESCE(
		 JSONB_OBJECT_AGG(
		      'id', tkt.ticket_type_id
		   ) || 
		   JSONB_OBJECT_AGG(
		      'type', tkt.ticket_type
		   ) || 
		   JSONB_OBJECT_AGG(
		      'img_path', tkt.ticket_type_img_path
		   ) || 
		   JSONB_OBJECT_AGG(
		      'description', tkt.ticket_type_description
		   ),
		 '{}'::jsonb
	) as corresponding_ticket_type,
	get_event_left_ticket_of_given_ticket_type(tp.id_event, tkt.ticket_type) AS left_tickets
FROM ticket_price tp
LEFT JOIN TicketType tkt ON tkt.ticket_type_id = tp.id_ticket_type
GROUP BY tp.id, tkt.ticket_type, tp.id_event;



-- ================	    USER	=========================

WITH
Top5Categories AS (
    SELECT
        u.email AS user_email,
        ec.id AS event_category_id,
        ec.event_category,
        ec.description AS event_category_description,
        COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket t WHERE t.user_email = u.email), 0) AS percentage
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
    U.is_active AS user_status,
    COALESCE(
            JSON_AGG(
                DISTINCT JSONB_BUILD_OBJECT(
                    'id', t5c.event_category_id,
                    'event_category', t5c.event_category,
                    'description', t5c.event_category_description
                )
            ) FILTER (WHERE t5c.event_category_id IS NOT NULL), '[]'
    ) AS user_top_5_categories
FROM "user" u
LEFT JOIN Top5Categories t5c ON u.email = t5c.user_email
GROUP BY u.email
LIMIT ? OFFSET ?;


--	===================	    MIX (EVENT_USER)	=============================
WITH EventCounts AS (
    SELECT id_event, COUNT(*) AS all_events_by_id
    FROM has_type
    GROUP BY id_event
    ORDER BY id_event
    LIMIT 2 OFFSET 0
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
     Top5Categories AS (
         SELECT
             u.email AS user_email,
             ec.id AS event_category_id,
             ec.event_category,
             ec.description AS event_category_description,
             COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket t WHERE t.user_email = u.email), 0) AS percentage
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
    u.is_active AS user_status,
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
GROUP BY e.id, c.created_at, c.updated_at, u.email, u.last_name, u.first_name, u.user_role, u.is_active, e.date_time
ORDER BY e.date_time DESC
LIMIT (SELECT COALESCE(SUM(all_events_by_id), 0) FROM EventCounts)
    OFFSET 0;

--	==================	USER_TICKET_EVENT	====================
WITH EventCounts AS (
    SELECT id_event, COUNT(*) AS all_events_by_id
    FROM has_type
    GROUP BY id_event
    ORDER BY id_event
    LIMIT ('1'::int8) OFFSET ('0'::int8)
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
Top5Categories AS (
     SELECT
         u.email AS user_email,
         ec.id AS event_category_id,
         ec.event_category,
         ec.description AS event_category_description,
         COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket t WHERE t.user_email = u.email), 0) AS percentage
     FROM ticket t
     RIGHT JOIN "user" u ON t.user_email = u.email
     LEFT JOIN ticket_price tp ON tp.id = t.id_ticket_price
     LEFT JOIN event e ON e.id = tp.id_event
     LEFT JOIN events_category ec ON e.category = ec.event_category
     GROUP BY ec.id, u.email
     ORDER BY percentage  DESC
     LIMIT 5
),
TicketType AS (
    SELECT
        tt.id AS ticket_type_id,
        tt.ticket_type AS ticket_type,
        tt.img_path AS ticket_type_img_path,
        tt.description AS ticket_type_description
    FROM tickets_type tt
)
SELECT e.id AS event_id, e.organizer AS event_organizer, e.title AS event_title, e.description AS event_description, e.date_time AS event_date_time, e.time_zone AS event_time_zone, e.location AS event_location, e.location_url AS event_location_url, e.image_path AS event_image_path, e.status AS event_status, e.category AS event_category, e.number_of_ticket AS event_number_of_ticket, e.max_ticket_per_user AS event_max_ticket_per_user, c.created_at AS event_created_at, c.updated_at AS event_updated_at, u.email AS user_email, u.last_name AS user_last_name, u.first_name AS user_first_name, u.profile_img_path AS user_img_profil_path, u.user_role AS user_role, u.is_active AS user_status, COALESCE(
      JSON_AGG(
            DISTINCT JSONB_BUILD_OBJECT(
                 'id', t5c.event_category_id,
                 'event_category', t5c.event_category,
                 'description', t5c.event_category_description
            )
      ) FILTER (WHERE t5c.event_category_id IS NOT NULL),
                            '[]'
) AS user_top_5_categories,
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
) AS event_types,
COALESCE(
      JSON_AGG(
           DISTINCT JSONB_BUILD_OBJECT(
               'id', tp.id,
               'price', tp.price,
               'currency', tp.currency,
               'created_at', tp.created_at,
               'max_number', tp.max_number,
               'associated_event_id', tp.id_event,
               'corresponding_ticket_type',
               JSONB_BUILD_OBJECT(
                      'id', tkt.ticket_type_id,
                      'event_category', tkt.ticket_type,
                      'img_path', tkt.ticket_type_img_path,
                       'description', tkt.ticket_type_description
               ),
              'left_tickets', get_event_left_ticket_of_given_ticket_type(tp.id_event, tkt.ticket_type)
           )
      ) FILTER (WHERE tp.id IS NOT NULL), '[]'
) AS event_left_tickets
, (
    SELECT ecy.id from event ev
    INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id
)   AS event_category_id
, (
    SELECT ecy.event_category from event ev
    INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id
)   AS event_category_id
, (
    SELECT ecy.description from event ev
    INNER JOIN events_category ecy ON ev.category = ecy.event_category WHERE ev.id = e.id
)   AS event_category_description
FROM event e 
INNER JOIN creates c ON c.id_event = e.id 
INNER JOIN "user" u ON u.email = c.user_email 
LEFT JOIN Top5Categories t5c ON u.email = t5c.user_email 
LEFT JOIN EventTypes ety ON e.id = ety.event_id 
LEFT JOIN events_category ec ON ec.event_category = e.category AND ec.id_event_type = ety.event_type_id 
LEFT JOIN ticket_price tp ON tp.id_event = e.id 
LEFT JOIN TicketType tkt ON tkt.ticket_type_id = tp.id_ticket_type 

WHERE e.id IN (SELECT id_event FROM EventCounts)
GROUP BY e.id, c.created_at, c.updated_at, u.email, u.last_name, u.first_name, u.user_role, u.is_active, e.date_time
ORDER BY e.date_time DESC
LIMIT (SELECT COALESCE(SUM(all_events_by_id), 0) FROM EventCounts)
OFFSET 0;

