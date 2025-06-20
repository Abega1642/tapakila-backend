--  THIS FILE HOLDS THE REAL DATA THAT ARE GOING TO BE USED IN THE APP

-- WEEKEND
-- event
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

INSERT INTO has_type (id_event, id_events_type) VALUES
    (
        (SELECT id FROM "event" WHERE title = 'Weeknd' AND location_url = 'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D'),
        (SELECT id FROM events_type WHERE event_type = 'MUSIC')
    );

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    30000.0,
    'MGA',
    '2025-02-01 00:00:00',
    6000,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Weeknd')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    40000.0,
    'MGA',
    '2025-02-01 00:00:00',
    2000,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Weeknd')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-02-01 00:00:00',
    2000,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Weeknd')
);

-- Tyla
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

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    30000.0,
    'MGA',
    '2024-12-12 00:00:00',
    6000,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Tyla')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    40000.0,
    'MGA',
    '2024-12-12 00:00:00',
    2000,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Tyla')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2024-12-12 00:00:00',
    2000,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Tyla')
);

-- Wicked
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

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-03-27 00:00:00',
    600,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'wicked')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    30000.0,
    'MGA',
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Wicked')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Wicked')
);

-- Zootopia
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

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-03-27 00:00:00',
    600,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Zootopia')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Zootopia')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Zootopia')
);

-- Valentine's day
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Valentine',
    'Valentine day party',
    '2025-02-14 19:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'La Balancoire Ivandry',
    'https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.8739638,47.5155097,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Cinema/Movie-screenings/Screening2.png',
    'NIGHTCLUB_PARTY',
    'COMPLETED',
    500,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-02-01 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Valentine')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-02-01 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Valentine')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',  
    '2025-02-01 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Valentine')
);

-- Nightclub1
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Night Party',
    'Night party at Carlton',
    '2025-04-12 20:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Hotel Carlton Anosy',
    'https://www.google.com/maps/place/Hotel+Carlton/@-18.9148433,47.515274,631m/data=!3m2!1e3!4b1!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Party/Nightlubl-parties/Nightclub1.png',
    'NIGHTCLUB_PARTY',
    'PUBLISHED',
    250,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Night Party')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-03-27 00:00:00',
    50,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Night Party')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Night Party')
);

-- Nightclub4
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Young Party',
    'Night party for young people',
    '2025-04-14 20:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'La city Ivandry',
    'https://www.google.com/maps/place/La+city+Ivandry/@-18.8751665,47.5170449,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f081cee5e14a9f:0x61982eb38c1d40e!8m2!3d-18.8751716!4d47.5196198!16s%2Fg%2F11g0qlrdz2?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Party/Nightlubl-parties/Nightclub4.png',
    'NIGHTCLUB_PARTY',
    'PUBLISHED',
    400,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Young Party')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    30000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Young Party')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Young Party')
);

-- Bob tobias
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Bob tobias',
    'Bob tobias business conference',
    '2025-04-07 10:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Sehatra Maitso Antsahamanitra',
    'https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116045,47.5195971,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Business/Business-conferences/Conference1.png',
    'BUSINESS_CONFERENCE',
    'PUBLISHED',
    300,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Bob tobias')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-03-27 00:00:00',
    50,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Bob tobias')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    10000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Bob tobias')
);

-- Church cinference
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Daniel Gallego',
    'Religious Conference wit Daniel Gallego',
    '2025-04-06 10:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'CCESCA Antanimena',
    'https://www.google.com/maps/place/Centre+Culturel+ESCA/@-18.8974177,47.5195832,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080aea42995b9:0xeb776d49b50eb79!8m2!3d-18.8974228!4d47.5221581!16s%2Fg%2F1thbn5gq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Church/Religious-conferences/Religious-conference1.png',
    'RELIGIOUS_CONFERENCE',
    'PUBLISHED',
    400,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Daniel Gallego')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Daniel Gallego')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    10000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Daniel Gallego')
);

-- Easter celebration
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Easter',
    'Night of Easter celebration',
    '2025-04-19 9:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Coliseum Antsonjombe',
    'https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Church/Spiritual-songs/Spiritual-song1.png',
    'SPIRITUAL_SONGS_AND_MUSIC',
    'PUBLISHED',
    500,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Easter')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Easter')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Easter')
);

-- Gaming Tournament1
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'E-sport',
    'E-sport Tournament',
    '2025-04-16 9:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Redzone Ankorondrano',
    'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735794,47.5181853,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Gaming-eSports/Tournaments/Tournament1.png',
    'ESPORT_TOURNAMENT',
    'PUBLISHED',
    200,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    20000.0,
    'MGA',
    '2025-03-27 00:00:00',
    50,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'E-sport')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    25000.0,
    'MGA',
    '2025-03-27 00:00:00',
    50,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'E-sport')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'E-sport')
);

-- Masterclass1
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Web Development',
    'Web development masterclass',
    '2025-04-26 8:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Sweety Ampefiloha',
    'https://www.google.com/maps/place/Sweety+Space/@-18.9147992,47.5137866,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07fa249d02497:0xed31c5ed5ae25591!8m2!3d-18.9148043!4d47.5163615!16s%2Fg%2F11ht58tvm7?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Education/Masterclasses/Masterclass1.png',
    'MASTERCLASS',
    'PUBLISHED',
    200,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    10000.0,
    'MGA',
    '2025-03-27 00:00:00',
    50,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Web Development')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',
    '2025-03-27 00:00:00',
    50,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Web Development')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    5000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Web Development')
);

-- Masterclass2
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Kids Robotic',
    'Kids Robotic masterclass',
    '2025-04-19 8:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'RedZone Ankorondrano',
    'https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735794,47.5181853,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Education/Masterclasses/Masterclass2.png',
    'MASTERCLASS',
    'PUBLISHED',
    400,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    10000.0,
    'MGA',
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Kids Robotic')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Kids Robotic')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    5000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    150,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Kids Robotic')
);

-- Workshop1
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Public Speaking',
    'Public Speaking workshop',
    '2025-04-22 8:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Canal Olympia',
    'https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'events-posters/Education/Workshops/Workshop1.png',
    'WORKSHOP',
    'PUBLISHED',
    500,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    10000.0,
    'MGA',
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Public Speaking')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',
    '2025-03-27 00:00:00',
    100,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Public Speaking')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    5000.0,
    'MGA',  
    '2025-03-27 00:00:00',
    200,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Public Speaking')
);

-- User buying ticket
INSERT INTO ticket (
    id, ticket_number, status, purchased_at, qr_code_path, payement_ref,
     ticket_owner_name, user_email, id_ticket_price, id_payment_mode
) VALUES (
    '$Tkt-' || gen_random_uuid(),
    6,
    true,
    '2025-03-27 00:00:00',
    '/',
    'ref',
    'Liantsoa',
    'liantsoa@gmail.com',
    (SELECT id from ticket_price where price = 30000.0),
    (SELECT id from payment_mode where provider = 'CASH')
);

INSERT INTO "user"
(email, profile_img_path, last_name, first_name, 
password, created_at, user_role, is_active)
VALUES (
    'hei.liantsoa@gmail.com',
    '/user/hei-liantsoa/profile-img/',
    'ratovo',
    'Liantsoa',
    '123',
    '2025-03-27 00:00:00',
    'USER',
    true
);

-- Batlle1
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Salford',
    'Boxing',
    'Boxing match',
    '2025-04-28 8:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'palais des Sports Mahamasina',
    'https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205828,47.5244095,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D',
    'event-posters/Sport/Battles/Battle1.png',
    'BATTLE',
    'PUBLISHED',
    1000,
    4
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    10000.0,
    'MGA',
    '2025-04-01 00:00:00',
    300,
    (SELECT id from tickets_type where ticket_type = 'STANDARD'),
    (SELECT id from event where title = 'Boxing')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    15000.0,
    'MGA',
    '2025-04-01 00:00:00',
    300,
    (SELECT id from tickets_type where ticket_type = 'VIP'),
    (SELECT id from event where title = 'Boxing')
);

INSERT INTO ticket_price (
    id, price, currency, created_at, max_number, id_ticket_type, id_event
) VALUES (
    '$TkP-' || gen_random_uuid(),
    5000.0,
    'MGA',  
    '2025-04-01 00:00:00',
    400,
    (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
    (SELECT id from event where title = 'Boxing')
);

