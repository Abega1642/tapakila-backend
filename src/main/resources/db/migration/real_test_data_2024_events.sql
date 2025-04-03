--  ##################     FIRST EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Deadpool and Wolverine',
             'Deadpool and Wolverine screening',
             '2024-04-06 8:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Cinepax',
             'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919696,47.5236875,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Cinema/Movie-screenings/Screening3.png',
             'MOVIE_SCREENING',
             'PUBLISHED',
             600,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Deadpool and Wolverine' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919696,47.5236875,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'CINEMA')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Deadpool and Wolverine' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919696,47.5236875,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-03-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Deadpool and Wolverine' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919696,47.5236875,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-03-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP'),
             (SELECT id FROM "event" WHERE title = 'Deadpool and Wolverine' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919696,47.5236875,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-03-27 02:00:00',
             400,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Deadpool and Wolverine' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919696,47.5236875,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     SECOND EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'New Year''s Eve',
             'New Year''s Eve show',
             '2024-12-31 21:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Cci Ivato',
             'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Party/Nightclub-parties/Nightclub3.png',
             'NEW_YEAR_EVE_PARTY',
             'PUBLISHED',
             1000,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'New Year''s Eve' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'PARTIES')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'New Year''s Eve' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-11-27 02:00:00',
             200,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'New Year''s Eve' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-11-27 02:00:00',
             200,
             (SELECT id from tickets_type where ticket_type = 'VIP'),
             (SELECT id FROM "event" WHERE title = 'New Year''s Eve' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             10000.0,
             'MGA',
             '2024-11-27 02:00:00',
             600,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'New Year''s Eve' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     THIRD EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Jazz Duo',
             'Jazz duo in Antsahamanitra',
             '2024-05-11 19:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Sehatra Maitso Antsahamanitra',
             'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116096,47.5195971,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Music/ConcertLive/Jazz/Jazz2.png',
             'LIVE_CONCERT',
             'PUBLISHED',
             300,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Jazz Duo' AND location_url = 'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116096,47.5195971,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Jazz Duo' AND location_url = 'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116096,47.5195971,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-03-27 02:00:00',
             50,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Jazz Duo' AND location_url = 'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116096,47.5195971,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-03-27 02:00:00',
             50,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Jazz Duo' AND location_url = 'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116096,47.5195971,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-03-27 02:00:00',
             200,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Jazz Duo' AND location_url = 'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116096,47.5195971,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     FOURTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Billie Eilish Live',
             'Billie Eilish at Barea',
             '2024-07-16 21:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Barea Mahamasina',
             'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Music/ConcertLive/Pop/Pop3.png',
             'LIVE_CONCERT',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Billie Eilish Live' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Billie Eilish Live' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-06-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Billie Eilish Live' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-06-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Billie Eilish Live' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-06-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Billie Eilish Live' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################    FIFTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Aaron Loeb',
             'Aaron Loeb Rock Concert',
             '2024-09-28 10:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Cci Ivato',
             'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Music/ConcertLive/Rock/Rock3.png',
             'ROCK_CONCERT',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Aaron Loeb' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Aaron Loeb' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-08-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Aaron Loeb' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-08-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Aaron Loeb' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-08-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Aaron Loeb' AND location_url = 'https://www.google.com/maps/search/cci/@-18.8919684,47.5056628,14z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     SIXTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Night Party',
             'Night Party at Carlton',
             '2024-04-20 20:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Carlton',
             'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148484,47.515274,17z/data=!3m1!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Party/Nightclub-parties/Nightclub4.png',
             'NIGHTCLUB_PARTY',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Night Party' AND location_url = 'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148484,47.515274,17z/data=!3m1!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'PARTIES')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Night Party' AND location_url = 'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148484,47.515274,17z/data=!3m1!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-03-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Night Party' AND location_url = 'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148484,47.515274,17z/data=!3m1!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-03-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Night Party' AND location_url = 'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148484,47.515274,17z/data=!3m1!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-03-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Night Party' AND location_url = 'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148484,47.515274,17z/data=!3m1!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     SEVENTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'New year',
             'New Year Party',
             '2024-01-01 8:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Coliseum Antsonjombe',
             'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699782,47.5416333,17z/data=!3m1!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Party/Nightclub-parties/Nightclub3.png',
             'NIGHTCLUB_PARTY',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'New year' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699782,47.5416333,17z/data=!3m1!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'PARTIES')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'New year' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699782,47.5416333,17z/data=!3m1!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2023-12-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'New year' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699782,47.5416333,17z/data=!3m1!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2023-12-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'New year' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699782,47.5416333,17z/data=!3m1!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2023-12-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'New year' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699782,47.5416333,17z/data=!3m1!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     EIGHTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Football match',
             'Football match in Barea',
             '2024-09-28 8:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Barea Mahamasina',
             'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Sport/Matches/Match2.png',
             'MATCH',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Football match' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'SPORTS')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Football match' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-08-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Football match' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-08-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Football match' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-08-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Football match' AND location_url = 'https://www.google.com/maps/search/barea/@-18.9116093,47.5118722,15z/data=!3m1!4b1?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     NINTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Coding Workshop',
             'Coding Workshop at RedZone',
             '2024-02-03 13:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Redzone Ankorondrano',
             'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735845,47.5181853,17z/data=!3m1!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Education/Workshops/Workshop2.png',
             'WORKSHOP',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Coding Workshop' AND location_url = 'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735845,47.5181853,17z/data=!3m1!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'EDUCATION')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Coding Workshop' AND location_url = 'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735845,47.5181853,17z/data=!3m1!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-01-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Coding Workshop' AND location_url = 'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735845,47.5181853,17z/data=!3m1!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-01-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Coding Workshop' AND location_url = 'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735845,47.5181853,17z/data=!3m1!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-01-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Coding Workshop' AND location_url = 'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735845,47.5181853,17z/data=!3m1!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     TENTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Christmas Ceremony',
             'Christmas Ceremony at Palais',
             '2024-12-25 8:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Palais de la Culture et des Sports',
             'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205879,47.5244095,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Church/Religious-ceremonies/Religious-ceremony2.png',
             'RELIGIOUS_CEREMONY',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Christmas Ceremony' AND location_url = 'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205879,47.5244095,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Christmas Ceremony' AND location_url = 'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205879,47.5244095,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-11-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Christmas Ceremony' AND location_url = 'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205879,47.5244095,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-11-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Christmas Ceremony' AND location_url = 'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205879,47.5244095,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-11-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Christmas Ceremony' AND location_url = 'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205879,47.5244095,17z/data=!3m1!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################     TWELTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Jazz Festival',
             'Jazz Festival at Arena',
             '2024-02-10 20:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Studio Arena Ivandry',
             'https://www.google.com/maps/place/Studio+Arena+Ivandry/@-18.8723465,47.5131075,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080c52f11a47f:0xc34a2cf2abc969c!8m2!3d-18.8723465!4d47.5156824!16s%2Fg%2F11fxzrfz11?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Music/Festival/Festival2.png',
             'JAZZ_FESTIVAL',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Jazz Festival' AND location_url = 'https://www.google.com/maps/place/Studio+Arena+Ivandry/@-18.8723465,47.5131075,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080c52f11a47f:0xc34a2cf2abc969c!8m2!3d-18.8723465!4d47.5156824!16s%2Fg%2F11fxzrfz11?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Jazz Festival' AND location_url = 'https://www.google.com/maps/place/Studio+Arena+Ivandry/@-18.8723465,47.5131075,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080c52f11a47f:0xc34a2cf2abc969c!8m2!3d-18.8723465!4d47.5156824!16s%2Fg%2F11fxzrfz11?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-01-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Jazz Festival' AND location_url = 'https://www.google.com/maps/place/Studio+Arena+Ivandry/@-18.8723465,47.5131075,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080c52f11a47f:0xc34a2cf2abc969c!8m2!3d-18.8723465!4d47.5156824!16s%2Fg%2F11fxzrfz11?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-01-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Jazz Festival' AND location_url = 'https://www.google.com/maps/place/Studio+Arena+Ivandry/@-18.8723465,47.5131075,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080c52f11a47f:0xc34a2cf2abc969c!8m2!3d-18.8723465!4d47.5156824!16s%2Fg%2F11fxzrfz11?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-01-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Jazz Festival' AND location_url = 'https://www.google.com/maps/place/Studio+Arena+Ivandry/@-18.8723465,47.5131075,17z/data=!3m1!4b1!4m6!3m5!1s0x21f080c52f11a47f:0xc34a2cf2abc969c!8m2!3d-18.8723465!4d47.5156824!16s%2Fg%2F11fxzrfz11?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
--  ##################    TWELTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Salford',
             'Halloween party',
             'Halloween party at Ivandry',
             '2024-10-31 21:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'La Balancoire',
             'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739689,47.5155097,17z/data=!3m1!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D',
             'events-posters/Party/Costume-parties/Costume-party1.png',
             'COSTUME_PARTY',
             'PUBLISHED',
             500,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Halloween party' AND location_url = 'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739689,47.5155097,17z/data=!3m1!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'PARTIES')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event"  WHERE title = 'Halloween party' AND location_url = 'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739689,47.5155097,17z/data=!3m1!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-09-27 02:00:00',
             100,
            (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Halloween party' AND location_url = 'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739689,47.5155097,17z/data=!3m1!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2024-09-27 02:00:00',
             100,
             (SELECT id from tickets_type where ticket_type = 'VIP').
             (SELECT id FROM "event" WHERE title = 'Halloween party' AND location_url = 'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739689,47.5155097,17z/data=!3m1!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2024-09-27 02:00:00',
             300,
            (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Halloween party' AND location_url = 'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739689,47.5155097,17z/data=!3m1!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?authuser=0&entry=ttu&g_ep=EgoyMDI1MDMzMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D')
         );
