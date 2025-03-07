-- Insert data into events_type
INSERT INTO events_type (id, event_type, description) VALUES 
    ('$EvT-' || gen_random_uuid(), 'BUSINESS', 'Events related to business, networking, and professional development.'),
    ('$EvT-' || gen_random_uuid(), 'ENTERTAINMENT', 'Events focused on stand-up comedy, magic, and artistic performances.'),
    ('$EvT-' || gen_random_uuid(), 'MUSIC', 'Live music performances, festivals, and DJ sets.'),
    ('$EvT-' || gen_random_uuid(), 'SPORTS', 'Sports competitions, matches, and outdoor activities.'),
    ('$EvT-' || gen_random_uuid(), 'THEATER_AND_SHOWS', 'Theater plays, musicals, operas, and dance performances.'),
    ('$EvT-' || gen_random_uuid(), 'EDUCATION', 'Workshops, masterclasses, and educational events.'),
    ('$EvT-' || gen_random_uuid(), 'CINEMA', 'Premiere screenings, movie screenings, and open-air cinema events.'),
    ('$EvT-' || gen_random_uuid(), 'EXHIBITIONS_AND_CULTURE', 'Art exhibitions, cultural fairs, and events showcasing art and culture.'),
    ('$EvT-' || gen_random_uuid(), 'FESTIVALS_AND_FAIRS', 'Cultural festivals, expos, carnivals, and geek conventions.'),
    ('$EvT-' || gen_random_uuid(), 'PARTIES', 'Nightclub parties, pool parties, and costume parties.'),
    ('$EvT-' || gen_random_uuid(), 'GAMING', 'Esport tournaments and gaming conferences.'),
    ('$EvT-' || gen_random_uuid(), 'RELIGIOUS', 'Religious ceremonies and spiritual music events.');

-- Insert data into events_category
INSERT INTO events_category (id, event_category, description, id_event_type) VALUES
    -- Entertainment
    ('$EvC-' || gen_random_uuid(), 'STAND_UP_COMEDY', 'Live comedy performances.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
    ('$EvC-' || gen_random_uuid(), 'MAGIC_AND_ILLUSIONS', 'Magic shows and illusion performances.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
    ('$EvC-' || gen_random_uuid(), 'ARTISTIC_PERFORMANCES', 'Various artistic performances, including theater and dance.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),

    -- Music
    ('$EvC-' || gen_random_uuid(), 'LIVE_CONCERT', 'Live music performances by artists or bands.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
    ('$EvC-' || gen_random_uuid(), 'MUSIC_FESTIVAL', 'A festival featuring various live music performances.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
    ('$EvC-' || gen_random_uuid(), 'DJ_SET', 'A DJ performance at a live event.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),

    -- Sports
    ('$EvC-' || gen_random_uuid(), 'MATCH', 'A competitive sports event or match.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
    ('$EvC-' || gen_random_uuid(), 'TOURNAMENT', 'A competitive sports competition or event.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
    ('$EvC-' || gen_random_uuid(), 'MARATHON_AND_RACE', 'Long-distance running events such as marathons and races.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
    ('$EvC-' || gen_random_uuid(), 'BATTLE', 'A competitive sports event, often featuring team competitions.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),

    -- Theater & Shows
    ('$EvC-' || gen_random_uuid(), 'THEATER_PLAY', 'A live performance of a play or drama.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
    ('$EvC-' || gen_random_uuid(), 'MUSICAL_COMEDY', 'A combination of comedy and musical performance.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
    ('$EvC-' || gen_random_uuid(), 'OPERA', 'A dramatic performance combining music and theater.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
    ('$EvC-' || gen_random_uuid(), 'BALLET_AND_DANCE', 'A dance performance, including ballet and other styles.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),

    -- Education
    ('$EvC-' || gen_random_uuid(), 'WORKSHOP', 'A hands-on, interactive educational event.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
    ('$EvC-' || gen_random_uuid(), 'MASTERCLASS', 'An expert-led educational class or session.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),

    -- Cinema
    ('$EvC-' || gen_random_uuid(), 'PREMIERE_SCREENING', 'The first public screening of a film.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
    ('$EvC-' || gen_random_uuid(), 'MOVIE_SCREENING', 'A public screening of a film or movie.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
    ('$EvC-' || gen_random_uuid(), 'OPEN_AIR_CINEMA', 'A cinema event held outdoors, often under the stars.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),

    -- Exhibitions & Culture
    ('$EvC-' || gen_random_uuid(), 'ART_EXHIBITION', 'A display of visual art, including paintings, sculptures, and photography.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
    ('$EvC-' || gen_random_uuid(), 'FAIR', 'A cultural fair or exhibition, often with arts and crafts.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),

    -- Festivals & Fairs
    ('$EvC-' || gen_random_uuid(), 'CULTURAL_FESTIVAL', 'A festival showcasing cultural performances and traditions.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
    ('$EvC-' || gen_random_uuid(), 'EXPO', 'An exhibition or fair showcasing innovations, products, and services.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
    ('$EvC-' || gen_random_uuid(), 'CARNIVAL', 'A festive event featuring parades, rides, and live entertainment.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
    ('$EvC-' || gen_random_uuid(), 'GEEK_CONVENTION', 'A convention dedicated to geek culture, including comics, gaming, and tech.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),

    -- Parties
    ('$EvC-' || gen_random_uuid(), 'NIGHTCLUB_PARTY', 'A party held at a nightclub with music and dancing.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
    ('$EvC-' || gen_random_uuid(), 'POOL_PARTY', 'A party held at a pool, often featuring music, drinks, and socializing.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
    ('$EvC-' || gen_random_uuid(), 'COSTUME_PARTY', 'A party where attendees dress in costumes or themed outfits.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),

    -- Gaming
    ('$EvC-' || gen_random_uuid(), 'ESPORT_TOURNAMENT', 'A competitive event featuring video gaming.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
    ('$EvC-' || gen_random_uuid(), 'GAMING_CONFERENCE', 'A conference focused on video games, development, and the gaming industry.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),

    -- Business
    ('$EvC-' || gen_random_uuid(), 'PROFESSIONAL_FAIR', 'An event where professionals can network and explore career opportunities.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
    ('$EvC-' || gen_random_uuid(), 'BUSINESS_CONFERENCE', 'A conference focused on business topics and networking.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),

    -- Religious
    ('$EvC-' || gen_random_uuid(), 'RELIGIOUS_CEREMONY', 'A religious service or ceremony.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
    ('$EvC-' || gen_random_uuid(), 'SPIRITUAL_SONGS_AND_MUSIC', 'An event featuring spiritual music and songs.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS'));


-- USERS
INSERT INTO "user" (email, last_name, first_name, password, user_role, status) VALUES
('michael.jones@example.com', 'Jones', 'Michael', 'hashed_password_789', 'USER', true),
('emma.white@example.com', 'White', 'Emma', 'hashed_password_012', 'ADMIN', true),
('oliver.green@example.com', 'Green', 'Oliver', 'hashed_password_345', 'USER', true);

--- ENTAIRTENMAINT / Stand-Up
INSERT INTO "event" (
    id, organizer, title, description, date_time, time_zone, 
    location, location_url, image_path, category, status, 
    number_of_ticket, max_ticket_per_user
)
VALUES (
    '$Ev-' || gen_random_uuid(), 
    'Comedy Club', 
    'Tefi Stand-Up', 
    'An evening of live stand-up comedy performances with Tefi',
    '2025-03-10 19:30:00+00', 
    'UTC_0_GREENWICH_MEAN_TIME', 
    'Gymnase couvert, Ankatso', 
    'https://www.comedyclub.com', 
    'event-posters/Entairtenment/Stand-up/StandUp1.jpg', 
    'STAND_UP_COMEDY', 
    'UPCOMING', 
    100, 
    5
),
-- Sports / Matches
(
    '$Ev-' || gen_random_uuid(), 
    'Sports Event Organizers', 
    'Basketball Match with HEI and ACCEM', 
    'A thrilling basketball match between top local teams.',
    '2025-03-15 15:00:00+03', 
    'UTC_3_EAST_AFRICA_TIME', 
    'Palais des sports Mahamasina', 
    'https://www.sportsevent.com', 
    'event-posters/Sport/Matches/Match1.png', 
    'MATCH', 
    'UPCOMING', 
    500, 
    5  
),
-- Theaters / Theater Play 
(
    '$Ev-' || gen_random_uuid(), 
    'Broadway Productions',       
    'Shakespeare in Analakely',     
    'A classical Shakespeare play',
    '2025-05-10 20:00:00',         
    'UTC_1_CENTRAL_EUROPEAN_TIME', 
    'tranompokonolona, Analakely',      
    'https://example.com/shakespeare-in-the-park', 
    'event-posters/Theaters/Theater-plays/Theater1.png',         
    'THEATER_PLAY',            
    'SCHEDULED',                 
    200,                           
    4                          
);

-- payment_mode
INSERT INTO payment_mode (id, description, payment_api_url, provider, type, created_at, updated_at, status) VALUES
('$PM-' || gen_random_uuid(), 'MVOLA Payment', 'https://api.paymentgateway.com/credit-card','MVOLA_MADAGASCAR', 'MOBILE_MONEY', current_timestamp, current_timestamp, true),
('$PM-' || gen_random_uuid(), 'Mobile Wallet Payment', 'https://api.paymentgateway.com/mobile-wallet', 'PAYPAL', 'PAYPAL', current_timestamp, current_timestamp, true),
('$PM-' || gen_random_uuid(), 'VISA Payment', 'https://api.paymentgateway.com/bank-transfer', 'BITCOIN', 'CRYPTOCURRENCY', current_timestamp, current_timestamp, false);

-- tickets_type
INSERT INTO tickets_type (id, ticket_type, description) VALUES
('$TT-' || gen_random_uuid(), 'VIP', 'VIP ticket with premium seating and exclusive access.'),
('$TT-' || gen_random_uuid(), 'STANDARD', 'Standard ticket with general admission.'),
('$TT-' || gen_random_uuid(), 'EARLY_BIRD', 'Early bird ticket with discounted pricing.');

-- tickets
INSERT INTO ticket (id, status, purchased_at, qr_code_path, payement_ref, ticket_owner_name, email_user, id_event, id_ticket_type, id_payment_mode)
VALUES
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket1.png', 'PAY123456', (SELECT first_name FROM user WHERE email = 'michael.jones@example.com'), (SELECT email FROM user WHERE email = 'michael.jones@example.com'), (SELECT id FROM event WHERE title = 'Tefi Stand-Up'), (SELECT id from tickets_type WHERE type = 'VIP'), (SELECT id FROM payment WHERE type = 'MVOLA_MADAGASCAR')),
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket2.png', 'PAY123457', (SELECT first_name FROM user WHERE email = 'michael.jones@example.com'), (SELECT email FROM user WHERE email = 'michael.jones@example.com'), (SELECT id FROM event WHERE title = 'Tefi Stand-Up'), (SELECT id from tickets_type WHERE type = 'STANDARD'), (SELECT id FROM payment WHERE type = 'MVOLA_MADAGASCAR')),
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket3.png', 'PAY123458', (SELECT first_name FROM user 'emma.white@example.com'), (SELECT email from user WHERE email = 'emma.white@example.com'), (SELECT id FROM event WHERE title = 'Basketball Match with HEI and ACCEM'), (SELECT id from tickets_type WHERE type = 'VIP'), (SELECT id FROM payment WHERE type = 'PAYPAL')),
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket4.png', 'PAY123459', (SELECT first_name FROM user 'emma.white@example.com'), (SELECT email from user WHERE email = 'emma.white@example.com'), (SELECT id FROM event WHERE title = 'Basketball Match with HEI and ACCEM'), (SELECT id from tickets_type WHERE type = 'EARLY_BIRD'), (SELECT id FROM payment WHERE type = 'PAYPAL')),
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket5.png', 'PAY123460', (SELECT first_name FROM user WHERE email = 'oliver.green@example.com'), (SELECT email from user WHERE email = 'oliver.green@example.com'), (SELECT id FROM event WHERE title = 'Shakespeare in Analakely'), (SELECT id from tickets_type WHERE type = 'VIP'), (SELECT id FROM payment WHERE type = 'BITCOIN')),
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket6.png', 'PAY123461', (SELECT first_name FROM user WHERE email = 'oliver.green@example.com'),(SELECT email from user WHERE email = 'oliver.green@example.com'), (SELECT id FROM event WHERE title = 'Shakespeare in Analakely'), (SELECT id from tickets_type WHERE type = 'STANDARD'), (SELECT id FROM payment WHERE type = 'BITCOIN')),
('$TT-' || gen_random_uuid()', true, NOW(), 'qr_codes/ticket7.png', 'PAY123462', (SELECT first_name FROM user WHERE email = 'oliver.green@example.com'), (SELECT email from user WHERE email = 'oliver.green@example.com'), (SELECT id FROM event WHERE title = 'Shakespeare in Analakely'), (SELECT id from tickets_type WHERE type = 'EARLY_BIRD'), (SELECT id FROM payment WHERE type = 'BITCOIN'));


-- ticket_price
INSERT INTO ticket_price (id, id_ticket_type, id_event, price, currency) VALUES
('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'VIP'), 
 (SELECT id FROM event WHERE title = 'Tefi Stand-Up'), 
 50000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'STANDARD'), 
 (SELECT id FROM event WHERE title = 'Tefi Stand-Up'), 
 30000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'EARLY_BIRD'), 
 (SELECT id FROM event WHERE title = 'Tefi Stand-Up'), 
 25000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'VIP'), 
 (SELECT id FROM event WHERE title = 'Basketball Match with HEI and ACCEM'), 
 10000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'STANDARD'), 
 (SELECT id FROM event WHERE title = 'Basketball Match with HEI and ACCEM'), 
 5000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'EARLY_BIRD'), 
 (SELECT id FROM event WHERE title = 'Basketball Match with HEI and ACCEM'), 
 3000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'VIP'), 
 (SELECT id FROM event WHERE title = 'Shakespeare in Analakely'), 
 75000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'STANDARD'), 
 (SELECT id FROM event WHERE title = 'Shakespeare in Analakely'), 
 50000, 'MGA'),

('$TP-' || gen_random_uuid(), 
 (SELECT id FROM tickets_type WHERE ticket_type = 'EARLY_BIRD'), 
 (SELECT id FROM event WHERE title = 'Shakespeare in Analakely'), 
 40000, 'MGA');

-- Insert data into creates for admin user (Emma White)
INSERT INTO creates (id, id_user, id_event, created_at) VALUES
('$Cr-' || gen_random_uuid(), 
 (SELECT id FROM "user" WHERE email = 'emma.white@example.com'), 
 (SELECT id FROM event WHERE title = 'Tefi Stand-Up'), 
 NOW());



