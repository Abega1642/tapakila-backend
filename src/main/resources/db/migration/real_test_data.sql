--  THIS FILE HOLDS THE REAL DATA THAT ARE GOING TO BE USED IN THE APP

--  ##############  FIRST EVENT ###################
        -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'RDJ',
             'Weeknd',
             'The weeknd concert',
             '2025-03-29 18:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Coliseum Antsonjombe',
             'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
             'events-posters/Music/ConcertLive/Pop/Pop4.png',
             'LIVE_CONCERT',
             'PUBLISHED',
             10000,
             8
         );
        -- INSERTION OF ITS TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Weeknd' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );

        -- WHO CREATES IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event" WHERE title = 'Weeknd' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
    );

        -- TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             30000.0,
             'MGA',
             '2025-02-01 00:00:00',
             6000,
             (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Weeknd' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             40000.0,
             'MGA',
             '2025-02-01 00:00:00',
             2000,
             (SELECT id from tickets_type where ticket_type = 'VIP'),
             (SELECT id FROM "event" WHERE title = 'Weeknd' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2025-02-01 00:00:00',
             2000,
             (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Weeknd' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
        );


-- ##################### SECOND EVENT  ############################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'RDJ',
             'Tyla',
             'Tyla concert',
             '2025-01-18 15:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Stade Barea Mahamasina',
             'https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
             'events-posters/Music/ConcertLive/Afrobeat/Afrobeat1.png',
             'LIVE-CONCERT',
             'COMPLETED',
             10000,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Tyla' AND location_url = 'https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event" WHERE title = 'Tyla' AND location_url = 'https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             30000.0,
             'MGA',
             '2024-12-12 00:00:00',
             6000,
             (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Tyla' AND location_url = 'https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             40000.0,
             'MGA',
             '2024-12-12 00:00:00',
             2000,
             (SELECT id from tickets_type where ticket_type = 'VIP'),
             (SELECT id FROM "event" WHERE title = 'Tyla' AND location_url = 'https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2024-12-12 00:00:00',
             2000,
             (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Tyla' AND location_url = 'https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         );

--  #############   THIRD EVENT  ################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Cinepax',
             'Wicked',
             'Wicked movie screening',
             '2025-04-09 21:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Cinepax tana water front',
             'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
             'events-posters/Cinema/Movie-screenings/Screening1.png',
             'MOVIE_SCREENING',
             'PUBLISHED',
             1000,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Wicked' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')
    ),
    (
        (SELECT id FROM "event" WHERE title = 'Wicked' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'CINEMA')
    );
--  INSERTION OF THE TYPE
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event" WHERE title = 'Wicked' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
    );
            --  WHO CREATED IT
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2025-03-27 00:00:00',
             600,
             (SELECT id from tickets_type where ticket_type = 'STANDARD'),
             (SELECT id FROM "event" WHERE title = 'Wicked' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             30000.0,
             'MGA',
             '2025-03-27 00:00:00',
             200,
             (SELECT id from tickets_type where ticket_type = 'VIP'),
             (SELECT id FROM "event" WHERE title = 'Wicked' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
        ),
         (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2025-03-27 00:00:00',
             200,
             (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
             (SELECT id FROM "event" WHERE title = 'Wicked' AND location_url = 'https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
         );
--  TICKET PRICE INFOS


--  ##################     FOURTH EVENT    ###################
            -- INSERTION OF THE EVENT
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path,
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
             '$Evt-' || gen_random_uuid(),
             'Canal Olympia',
             'Zootopia 2',
             'Zootopia 2 movie screening',
             '2025-04-10 10:00:00',
             'UTC_3_EAST_AFRICA_TIME',
             'Canal Olympia Andohotapenaka',
             'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
             'events-posters/Cinema/Movie-screenings/Screening2.png',
             'MOVIE_SCREENING',
             'PUBLISHED',
             1000,
             4
         );
            --  INSERTION OF THE TYPE
INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Zootopia 2' AND location_url = 'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')
    ),
    (
        (SELECT id FROM "event" WHERE title = 'Zootopia 2' AND location_url = 'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'CINEMA')
    );
            --  WHO CREATED IT
INSERT INTO creates (user_email, id_event) VALUES
    (
        'tapakila.noreply@gmail.com',
        (SELECT id FROM "event" WHERE title = 'Zootopia 2' AND location_url = 'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D')
    );
            --  TICKET PRICE INFOS
INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
             '$TkP-' || gen_random_uuid(),
             20000.0,
             'MGA',
             '2025-03-27 00:00:00',
             600,
             (SELECT id FROM "event" WHERE title = 'Zootopia 2' AND location_url = 'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
             (SELECT id from event where title = 'Zootopia 2')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             25000.0,
             'MGA',
             '2025-03-27 00:00:00',
             200,
             (SELECT id FROM "event" WHERE title = 'Zootopia 2' AND location_url = 'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
             (SELECT id from event where title = 'Zootopia 2')
         ),
         (
             '$TkP-' || gen_random_uuid(),
             15000.0,
             'MGA',
             '2025-03-27 00:00:00',
             200,
             (SELECT id FROM "event" WHERE title = 'Zootopia 2' AND location_url = 'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
             (SELECT id from event where title = 'Zootopia 2')
         );
