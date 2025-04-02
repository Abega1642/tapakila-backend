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
     'https://maps.example.com/central-park',
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
     25000,
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
     30000,
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
     20000,
     'MGA',  
     '2025-03-27 00:00:00',
     150,
     (SELECT id from tickets_type where ticket_type = 'EARLY_BIRD'),
     (SELECT id from event where title = 'Young Party')
 );