INSERT INTO events_type (id, event_type, description) VALUES
    ('$EvT-' || gen_random_uuid(), 'BUSINESS_PROFESSIONAL', 'Events related to business, networking, and professional development.'),
    ('$EvT-' || gen_random_uuid(), 'ENTERTAINMENT_ARTS', 'Events focused on music, theater, and various artistic performances.'),
    ('$EvT-' || gen_random_uuid(), 'SPORTS_OUTDOOR', 'Sports competitions, outdoor activities, and adventure events.'),
    ('$EvT-' || gen_random_uuid(), 'TECH_INNOVATION', 'Events related to technology, innovation, and startups.'),
    ('$EvT-' || gen_random_uuid(), 'COMMUNITY_SOCIAL', 'Community gatherings, social awareness, and charity events.'),
    ('$EvT-' || gen_random_uuid(), 'EDUCATION_TRAINING', 'Educational events, conferences, and training sessions.'),
    ('$EvT-' || gen_random_uuid(), 'FOOD_DRINKS', 'Food festivals, cooking workshops, and tasting events.'),
    ('$EvT-' || gen_random_uuid(), 'HEATH_WELLNESS', 'Health, wellness, and fitness-oriented events.');


-- Insert data into events_category
INSERT INTO events_category (id, event_category, description, id_event_type) VALUES
    -- Business & Professional
    ('$EvC-' || gen_random_uuid(), 'CONFERENCE', 'A formal meeting for discussion on a specific topic.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'WORKSHOP', 'A hands-on training session or educational event.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'MEETUP', 'An informal gathering of people with common interests.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'NETWORKING', 'An event where professionals connect and build relationships.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'SEMINAR', 'A lecture or presentation on a specific topic.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'SUMMIT', 'A high-level meeting or conference.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'EXPO', 'An exhibition showcasing products, services, or ideas.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'TRADE_SHOW', 'A large-scale business exhibition.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),
    ('$EvC-' || gen_random_uuid(), 'PANEL_DISCUSSION', 'A discussion among experts on a specific topic.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS_PROFESSIONAL')),

    -- Entertainment & Arts
    ('$EvC-' || gen_random_uuid(), 'CONCERT', 'Live music performances by artists or bands.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'FESTIVAL', 'A large-scale cultural or entertainment event.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'THEATER', 'A live performance of a play or drama.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'COMEDY_SHOW', 'A live comedic performance.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'CINEMA_SCREENING', 'A public film screening event.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'DANCE_SHOW', 'A performance showcasing dance talent.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'ART_EXHIBITION', 'A display of visual artworks.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),
    ('$EvC-' || gen_random_uuid(), 'LITERARY_EVENT', 'A gathering focused on books and literature.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT_ARTS')),

    -- Sports & Outdoor
    ('$EvC-' || gen_random_uuid(), 'SPORTS', 'A competitive sports event or match.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),
    ('$EvC-' || gen_random_uuid(), 'TOURNAMENT', 'A structured sports competition.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),
    ('$EvC-' || gen_random_uuid(), 'MARATHON', 'A long-distance running competition.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),
    ('$EvC-' || gen_random_uuid(), 'CYCLING_EVENT', 'A cycling race or competition.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),
    ('$EvC-' || gen_random_uuid(), 'E_SPORTS', 'Competitive video gaming tournaments.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),
    ('$EvC-' || gen_random_uuid(), 'ADVENTURE_RACE', 'An extreme outdoor endurance challenge.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),
    ('$EvC-' || gen_random_uuid(), 'OUTDOOR_CAMPING', 'An event focused on nature and camping activities.', (SELECT id FROM events_type WHERE event_type = 'SPORTS_OUTDOOR')),

    -- Tech & Innovation
    ('$EvC-' || gen_random_uuid(), 'HACKATHON', 'A coding event where programmers collaborate intensively.', (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),
    ('$EvC-' || gen_random_uuid(), 'CODING_BOOTCAMP', 'An intensive programming training session.', (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),
    ('$EvC-' || gen_random_uuid(), 'STARTUP_PITCH', 'A competition where startups present their ideas.', (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),
    ('$EvC-' || gen_random_uuid(), 'WEBINAR', 'An online seminar or workshop.', (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),
    ('$EvC-' || gen_random_uuid(), 'TECH_FAIR', 'An event showcasing new technologies.', (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),
    ('$EvC-' || gen_random_uuid(), 'PRODUCT_LAUNCH', 'An event introducing a new product.', (SELECT id FROM events_type WHERE event_type = 'TECH_INNOVATION')),

    -- Community & Social
    ('$EvC-' || gen_random_uuid(), 'CHARITY_EVENT', 'An event organized to raise funds for a cause.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_SOCIAL')),
    ('$EvC-' || gen_random_uuid(), 'FUNDRAISER', 'An event to collect money for a cause.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_SOCIAL')),
    ('$EvC-' || gen_random_uuid(), 'RELIGIOUS_GATHERING', 'A gathering for religious purposes.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_SOCIAL')),
    ('$EvC-' || gen_random_uuid(), 'POLITICAL_RALLY', 'A political event supporting a cause or candidate.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_SOCIAL')),
    ('$EvC-' || gen_random_uuid(), 'PRIDE_EVENT', 'An event celebrating LGBTQ+ pride.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_SOCIAL')),

    -- Education & Training
    ('$EvC-' || gen_random_uuid(), 'COLLEGE_FAIR', 'An event for students exploring educational opportunities.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION_TRAINING')),
    ('$EvC-' || gen_random_uuid(), 'GRADUATION_CEREMONY', 'An event celebrating academic achievements.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION_TRAINING')),
    ('$EvC-' || gen_random_uuid(), 'TUTORING_SESSION', 'A session focused on academic assistance.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION_TRAINING')),
    ('$EvC-' || gen_random_uuid(), 'PUBLIC_LECTURE', 'An educational talk open to the public.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION_TRAINING'));

