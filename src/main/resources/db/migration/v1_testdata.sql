INSERT INTO "user" (email, last_name, first_name, password, user_role, status) VALUES
('john.doe@example.com', 'Doe', 'John', 'hashed_password_123', 'USER', true),
('jane.smith@example.com', 'Smith', 'Jane', 'hashed_password_456', 'ADMIN', true);

INSERT INTO "event" (id, organizer, title, description, date_time, time_zone, location, location_url, image_path, category, status, number_of_ticket, max_ticket_per_user) VALUES
('$EVT-' || gen_random_uuid(), 'John Doe', 'Tech Conference 2025', 'A major conference on emerging tech trends.', '2025-06-15 09:00:00', 'UTC_0_GREENWICH_MEAN_TIME', 'London, UK', 'https://maps.example.com/london', '/images/tech_conference.jpg', 'CONFERENCE', 'PUBLISHED', 500, 5),
('$EVT-' || gen_random_uuid(), 'Jane Smith', 'International Music Festival', 'An amazing lineup of international artists.', '2025-07-22 18:00:00', 'UTC_1_CENTRAL_EUROPEAN_TIME', 'Paris, France', 'https://maps.example.com/paris', '/images/music_festival.jpg', 'FESTIVAL', 'SCHEDULED', 1000, 2);

INSERT INTO creates (user_email, id_event, created_at, updated_at) VALUES
('john.doe@example.com', (SELECT id FROM "event" WHERE title = 'Tech Conference 2025'), current_timestamp, current_timestamp),
('jane.smith@example.com', (SELECT id FROM "event" WHERE title = 'International Music Festival'), current_timestamp, current_timestamp);

INSERT INTO has_type (id_event, id_events_type) VALUES
((SELECT id FROM "event" WHERE title = 'Tech Conference 2025'), (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),
((SELECT id FROM "event" WHERE title = 'Tech Conference 2025'), (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
((SELECT id FROM "event" WHERE title = 'International Music Festival'), (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
((SELECT id FROM "event" WHERE title = 'International Music Festival'), (SELECT id FROM events_type WHERE event_type = 'EDUCATION_TRAINING')),
((SELECT id FROM "event" WHERE title = 'International Music Festival'), (SELECT id FROM events_type WHERE event_type = 'HEATH_WELLNESS'));


