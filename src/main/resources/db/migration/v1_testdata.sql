INSERT INTO "user" (email, profile_img_path, last_name, first_name, password, user_role, is_active) VALUES
('admin@example.com', '/user/admin/profile.jpg', 'Doe', 'John', 'hashed_password_123', 'ADMIN', true);

INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, location, location_url, image_path, 
    category, status, number_of_ticket, max_ticket_per_user
) VALUES (
    '$Evt-' || gen_random_uuid(),
    'Music Fest Organizers',
    'Annual Summer Music Festival',
    'Join us for the biggest music festival of the year! Featuring top artists from around the world.',
    '2024-07-15 18:00:00',
    'UTC_1_CENTRAL_EUROPEAN_TIME',
    'Central Park, New York City',
    'https://maps.example.com/central-park',
    '/images/events/music_festival.jpg',
    'MUSIC_FESTIVAL',
    'PUBLISHED',
    10000,
    4
),
-- This insert is related to resources/static/event-poster/Party/NightClub-parties/Nightclub1.jpg
(
    '$Evt-' || gen_random_uuid(),
    'Farthotel',
    'Night Party',
    'Join us for an unforgettable night party at Hotel Carlton! Featuring special performances and a star-studded guest list.',
    '2025-02-28 20:00:00',
    'UTC_1_CENTRAL_EUROPEAN_TIME',
    'Hotel Carlton',
    'https://maps.example.com/hotel-carlton',
    '/images/events/night_party.jpg',
    'NIGHTCLUB_PARTY',
    'PUBLISHED',
    500,
    2
),
-- This insert is related to resources/static/event-poster/Theaters/Operas/Opera1.png
(
    '$Evt-' || gen_random_uuid(),
    'Studio Showcase',
    'Annual Studio Showcase',
    'A night filled with mesmerizing performances, exquisite music, and a live orchestra. Join us for an unforgettable evening!',
    '2025-04-15 19:00:00',
    'UTC_1_CENTRAL_EUROPEAN_TIME',
    'La Scène D''ivandry',
    'https://maps.example.com/la-scene-divandry',
    '/images/events/studio_showcase.jpg',
    'OPERA',
    'PUBLISHED',
    300,
    2
),
-- This insert is related to resources/static/event-poster/Theaters/Theater-plays/Theater1.png
(
    '$Evt-' || gen_random_uuid(),
    'Theater Play Organizers',
    'Theater Play',
    'A time for you to discover the magic of theater! Join us for an unforgettable evening of drama and performance.',
    '2025-03-24 19:00:00',
    'UTC_3_EAST_AFRICA_TIME',
    'Tranompokonolona Analakely',
    'https://maps.example.com/tranompokonolona-analakely',
    '/images/events/theater_play.jpg',
    'THEATER_PLAY',
    'PUBLISHED',
    200,
    4
);




INSERT INTO has_type (id_event, id_events_type) VALUES
(
    (SELECT id FROM "event" WHERE title = 'Annual Summer Music Festival'),
    (SELECT id FROM events_type WHERE event_type = 'MUSIC')
),
(
    (SELECT id FROM "event" WHERE title = 'Annual Summer Music Festival'),
    (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')
),
-- This insert is related to resources/static/event-poster/Party/NightClub-parties/Nightclub1.jpg

(
    (SELECT id FROM "event" WHERE title = 'Night Party'),
    (SELECT id FROM events_type WHERE event_type = 'PARTIES')
),
-- This insert is related to resources/static/event-poster/Theaters/Operas/Opera1.png
(
    (SELECT id FROM "event" WHERE title = 'Annual Studio Showcase'),
    (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')
),

-- This insert is related to resources/static/event-poster/Theaters/Theater-plays/Theater1.png

(
    (SELECT id FROM "event" WHERE title = 'Theater Play'),
    (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')
);





INSERT INTO creates (user_email, id_event) VALUES
(
    'admin@example.com',
    (SELECT id FROM "event" WHERE title = 'Annual Summer Music Festival')
),
-- This insert is related to resources/static/event-poster/Party/NightClub-parties/Nightclub1.jpg
(
    'admin@example.com',
    (SELECT id FROM "event" WHERE title = 'Night Party')
),
-- This insert is related to resources/static/event-poster/Theaters/Operas/Opera1.png
(
    'admin@example.com',
    (SELECT id FROM "event" WHERE title = 'Annual Studio Showcase')
),
(
    'admin@example.com',
    (SELECT id FROM "event" WHERE title = 'Theater Play')
);

INSERT INTO ticket_price (id, id_ticket_type, id_event, price, currency) VALUES
(
    '$TkP-' || gen_random_uuid(),
    (SELECT id FROM tickets_type WHERE ticket_type = 'VIP'),
    (SELECT id FROM event WHERE title = 'Annual Summer Music Festival'),
    50000, 'MGA'
),
(
    '$TkP-' || gen_random_uuid(),
    (SELECT id FROM tickets_type WHERE ticket_type = 'STANDARD'),
    (SELECT id FROM event WHERE title = 'Annual Summer Music Festival'),
    30000, 'MGA'
),
(
    '$TkP-' || gen_random_uuid(),
    (SELECT id FROM tickets_type WHERE ticket_type = 'EARLY_BIRD'),
    (SELECT id FROM event WHERE title = 'Annual Summer Music Festival'),
    25000, 'MGA'
),

(
    '$TkP-' || gen_random_uuid(),
    (SELECT id FROM tickets_type WHERE ticket_type = 'VIP'),
    (SELECT id FROM event WHERE title = 'Night Party'),
    5000, 'MGA'
),
(
    '$TkP-' || gen_random_uuid(),
    (SELECT id FROM tickets_type WHERE ticket_type = 'STANDARD'),
    (SELECT id FROM event WHERE title = 'Night Party'),
    3000, 'MGA'
),
(
    '$TkP-' || gen_random_uuid(),
    (SELECT id FROM tickets_type WHERE ticket_type = 'EARLY_BIRD'),
    (SELECT id FROM event WHERE title = 'Night Party'),
    2500, 'MGA'
);




-- tickets
INSERT INTO ticket (id, qr_code_path, payement_ref, ticket_owner_name, user_email, id_ticket_price, id_payment_mode) VALUES
(
 '$Tkt-' || gen_random_uuid(),
 'qr_codes/ticket1.png',
 'PAY123456',
 'Just some random owner',
 'admin@example.com',
 (SELECT id from ticket_price WHERE id_event = '$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd' AND price = 50000),
 '$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed'
),
(
 '$Tkt-' || gen_random_uuid(),
 'qr_codes/ticket1.png',
 'PAY123456',
 'Just some random owner',
 'admin@example.com',
 (SELECT id from ticket_price WHERE id_event = '$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd' AND price = 50000),
 '$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed'
),
(
    '$Tkt-' || gen_random_uuid(),
    'qr_codes/ticket1.png',
    'PAY123454',
    'Just some random owner2',
    'admin@example.com',
    (SELECT id from ticket_price WHERE id_event = '$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd' AND price = 25000),
    '$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed'
),

(
    '$Tkt-' || gen_random_uuid(),
    'qr_codes/ticket1.png',
    'PAY123456',
    'Just some random owner',
    'admin@example.com',
    (SELECT id from ticket_price WHERE id_event = '$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2' AND price = 5000),
    '$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed'
),
(
    '$Tkt-' || gen_random_uuid(),
    'qr_codes/ticket1.png',
    'PAY123454',
    'Just some random owner2',
    'admin@example.com',
    (SELECT id from ticket_price WHERE id_event = '$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2' AND price = 2500),
    '$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed'
),
(
    '$Tkt-' || gen_random_uuid(),
    'qr_codes/ticket1.png',
    'PAY123454',
    'Just some random owner2',
    'admin@example.com',
    (SELECT id from ticket_price WHERE id_event = '$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2' AND price = 3000),
    '$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed'
);


