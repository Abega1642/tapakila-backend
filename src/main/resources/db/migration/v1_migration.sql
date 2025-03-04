CREATE ROLE tapakila_db_admin WITH LOGIN PASSWORD 'tapakila-db-admin-3bew';

DROP DATABASE IF EXISTS tapakila_db;
CREATE DATABASE tapakila_db;

\c tapakila_db;


GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES IN SCHEMA public TO tapakila_db_admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, INSERT, DELETE ON TABLES TO tapakila_db_admin;


GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO tapakila_db_admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT USAGE, SELECT ON SEQUENCES TO tapakila_db_admin;

CREATE TYPE event_type AS ENUM (
    'ENTERTAINMENT',
    'MUSIC',
    'SPORTS',
    'THEATER_AND_SHOWS',
    'EDUCATION',
    'CINEMA',
    'EXHIBITIONS_AND_CULTURE',
    'FESTIVALS_AND_FAIRS',
    'PARTIES',
    'GAMING',
    'BUSINESS',
    'RELIGIOUS',
    'FOOD_AND_DRINK',
    'CHARITY',
    'TRAVEL',
    'FASHION',
    'TECHNOLOGY',
    'ENVIRONMENTAL',
    'FAMILY_AND_KIDS',
    'HEALTH_AND_WELLNESS',
    'LITERATURE_AND_BOOKS',
    'COMEDY',
    'NIGHTLIFE',
    'OUTDOOR_AND_ADVENTURE',
    'COMMUNITY_AND_SOCIAL',
    'HISTORY_AND_HERITAGE',
    'SCIENCE_AND_TECH',
    'ANIMALS_AND_NATURE'
);


CREATE TYPE event_category AS ENUM (
    -- Entertainment
    'MAGIC_AND_ILLUSIONS',
    'ARTISTIC_PERFORMANCES',
    'STREET_PERFORMANCES',
    'CIRCUS_SHOW',
    'VARIETY_SHOW',
    'PUPPETRY',
    'STORYTELLING',

    -- Music
    'LIVE_CONCERT',
    'MUSIC_FESTIVAL',
    'DJ_SET',
    'ORCHESTRA',
    'OPERA_FESTIVAL',
    'ACOUSTIC_CONCERT',
    'JAZZ_FESTIVAL',
    'ROCK_CONCERT',
    'CLASSICAL_MUSIC',
    'KARAOKE_NIGHT',

    -- Sports
    'MATCH',
    'TOURNAMENT',
    'MARATHON_AND_RACE',
    'BATTLE',
    'WATER_SPORTS',
    'WINTER_SPORTS',
    'CYCLING_RACE',
    'TRIATHLON',
    'EXTREME_SPORTS',
    'EQUESTRIAN_EVENT',
    'FITNESS_CHALLENGE',

    -- Theater & Shows
    'THEATER_PLAY',
    'MUSICAL_COMEDY',
    'OPERA',
    'BALLET_AND_DANCE',
    'IMPROV_THEATER',
    'PUPPET_SHOW',
    'CABARET',
    'MIME_SHOW',
    'ONE_MAN_SHOW',
    'CHILDREN_THEATER',

    -- Education
    'WORKSHOP',
    'MASTERCLASS',
    'SEMINAR',
    'ONLINE_COURSE',
    'LECTURE',
    'HACKATHON',
    'CODING_BOOTCAMP',
    'LANGUAGE_EXCHANGE',

    -- Cinema
    'PREMIERE_SCREENING',
    'MOVIE_SCREENING',
    'OPEN_AIR_CINEMA',
    'FILM_FESTIVAL',
    'DOCUMENTARY_SCREENING',
    'CLASSIC_MOVIE_SCREENING',
    'ANIME_SCREENING',
    'SHORT_FILM_FESTIVAL',
    'SILENT_MOVIE_NIGHT',

    -- Exhibitions & Culture
    'ART_EXHIBITION',
    'FAIR',
    'SCIENCE_EXHIBITION',
    'HISTORY_EXHIBITION',
    'CULTURAL_TOUR',
    'PHOTOGRAPHY_EXHIBITION',
    'SCULPTURE_EXHIBITION',
    'HISTORICAL_REENACTMENT',
    'CULTURAL_WORKSHOP',

    -- Festivals & Fairs
    'CULTURAL_FESTIVAL',
    'EXPO',
    'CARNIVAL',
    'GEEK_CONVENTION',
    'FOOD_FESTIVAL',
    'WINE_FESTIVAL',
    'BOOK_FAIR',
    'COMIC_CON',
    'HARVEST_FESTIVAL',
    'FLOWER_FESTIVAL',

    -- Parties
    'NIGHTCLUB_PARTY',
    'POOL_PARTY',
    'COSTUME_PARTY',
    'BEACH_PARTY',
    'THEMED_PARTY',
    'GLOW_PARTY',
    'SILENT_DISCO',
    'ROOFTOP_PARTY',
    'NEW_YEAR_EVE_PARTY',

    -- Gaming
    'ESPORT_TOURNAMENT',
    'GAMING_CONFERENCE',
    'LAN_PARTY',
    'VIDEO_GAME_RELEASE',
    'BOARD_GAME_NIGHT',
    'COSPLAY_EVENT',
    'GAMING_MARATHON',
    'VIRTUAL_REALITY_EXPO',

    -- Business
    'PROFESSIONAL_FAIR',
    'BUSINESS_CONFERENCE',
    'STARTUP_PITCH_EVENT',
    'CORPORATE_SOCIAL',
    'NETWORKING_EVENT',
    'INVESTOR_PITCH',
    'TRADE_SHOW',
    'CORPORATE_TRAINING',

    -- Religious
    'RELIGIOUS_CEREMONY',
    'SPIRITUAL_SONGS_AND_MUSIC',
    'PILGRIMAGE',
    'FAITH_HEALING',
    'SPIRITUAL_RETREAT',
    'PRAYER_MEETING',
    'RELIGIOUS_CONFERENCE',
    'HOLY_DAY_CELEBRATION',

    -- Food and Drink
    'WINE_TASTING',
    'BEER_FESTIVAL',
    'COFFEE_EXPO',
    'COCKTAIL_WORKSHOP',
    'CHOCOLATE_FESTIVAL',
    'STREET_FOOD_FESTIVAL',

    -- Charity
    'CHARITY_GALA',
    'CHARITY_RUN',
    'VOLUNTEER_EVENT',
    'FUNDRAISING_GALA',
    'CHARITY_AUCTION',
    'COMMUNITY_SERVICE',

    -- Travel
    'TRAVEL_EXPO',
    'ADVENTURE_TOUR',
    'EXOTIC_TRAVEL',
    'CRUISE_EVENT',
    'ADVENTURE_CAMP',
    'CULTURAL_IMMERSION',

    -- Fashion
    'FASHION_SHOW',
    'FASHION_EXPO',
    'FASHION_WORKSHOP',
    'FASHION_AWARD',
    'POP_UP_STORE',
    'DESIGNER_SHOWCASE',

    -- Technology
    'TECH_CONFERENCE',
    'GADGET_EXPO',
    'INNOVATION_SUMMIT',
    'AI_CONFERENCE',
    'ROBOTICS_EXPO',
    'CYBERSECURITY_SUMMIT',

    -- Environmental
    'CLEANUP_EVENT',
    'GREEN_CONFERENCE',
    'ECO_FESTIVAL',
    'TREE_PLANTING',
    'SUSTAINABILITY_WORKSHOP',
    'ECO_FRIENDLY_MARKET',

    -- Family & Kids
    'KIDS_THEATER',
    'FAMILY_CARNIVAL',
    'CHILDREN_WORKSHOP',
    'AMUSEMENT_PARK_EVENT',
    'STORYTELLING_SESSION',

    -- Health & Wellness
    'YOGA_CLASS',
    'MEDITATION_SESSION',
    'HEALTH_FITNESS_CHALLENGE',
    'WELLNESS_RETREAT',
    'HEALTH_FAIR',

    -- Literature & Books
    'BOOK_SIGNING',
    'AUTHOR_TALK',
    'POETRY_READING',
    'WRITING_WORKSHOP',
    'BOOK_CLUB_MEETING',

    -- Comedy
    'STAND_UP_COMEDY',
    'COMEDY_CLUB_NIGHT',
    'SATIRE_SHOW',
    'IMPROV_COMEDY',
    'COMEDY_FESTIVAL',

    -- Nightlife
    'NIGHTCLUB_EVENT',
    'LOUNGE_PARTY',
    'NIGHTLIFE_KARAOKE_NIGHT',
    'THEMED_NIGHT',
    'AFTER_PARTY',

    -- Outdoor & Adventure
    'HIKING_TOUR',
    'CAMPING_EVENT',
    'ROCK_CLIMBING',
    'KAYAKING_TOUR',
    'ADVENTURE_RACE',

    -- Community & Social
    'NEIGHBORHOOD_FAIR',
    'COMMUNITY_POTLUCK',
    'VOLUNTEER_MEETUP',
    'SOCIAL_CLUB_EVENT',
    'CULTURAL_EXCHANGE',

    -- History & Heritage
    'HISTORICAL_TOUR',
    'REENACTMENT_EVENT',
    'HERITAGE_FESTIVAL',
    'ARCHAEOLOGY_TALK',
    'MUSEUM_NIGHT',

    -- Science & Tech
    'SCIENCE_FAIR',
    'TECH_DEMO',
    'STEM_WORKSHOP',
    'ASTRONOMY_NIGHT',
    'INNOVATION_EXPO',

    -- Animals & Nature
    'WILDLIFE_TOUR',
    'PET_ADOPTION_EVENT',
    'BIRD_WATCHING',
    'NATURE_PHOTOGRAPHY',
    'ANIMAL_SANCTUARY_VISIT'
);

CREATE TYPE event_status AS ENUM (
    'DRAFT',
    'PUBLISHED',
    'SCHEDULED',
    'ONGOING',
    'COMPLETED',
    'CANCELED'
);

CREATE TYPE ticket_type AS ENUM (
    'VIP',
    'STANDARD',
    'EARLY_BIRD'
);  -- To be extended if we want

CREATE TYPE payment_mode_type AS ENUM (
    'MOBILE_MONEY',
    'VISA',
    'MASTERCARD',
    'PAYPAL',
    'CRYPTOCURRENCY',
    'BITCOIN',
    'CASH'
);

CREATE TYPE payment_mode_provider AS ENUM (
    'MVOLA_MADAGASCAR',
    'ORANGE_MONEY',
    'AIRTEL_MONEY',
    'PAYPAL',
    'STRIPE',
    'VISA',
    'BANK_TRANSFER',
    'MASTERCARD',
    'MTN_MOBILE_MONEY',
    'GOOGLE_PAY',
    'APPLE_PAY',
    'BITCOIN',
	'CASH'
);   -- To be extended

CREATE TYPE user_role AS ENUM (
    'ADMIN',
    'USER'
);

CREATE TYPE time_zone AS ENUM (
    -- UTC -12 to UTC -1
    'UTC_MINUS_12_BAKER_ISLAND',
    'UTC_MINUS_11_SAMOA_TIME',
    'UTC_MINUS_10_HAWAII_ALEUTIAN_STANDARD_TIME',
    'UTC_MINUS_9_ALASKA_STANDARD_TIME',
    'UTC_MINUS_8_PACIFIC_STANDARD_TIME',
    'UTC_MINUS_7_MOUNTAIN_STANDARD_TIME',
    'UTC_MINUS_6_CENTRAL_STANDARD_TIME',
    'UTC_MINUS_5_EASTERN_STANDARD_TIME',
    'UTC_MINUS_4_ATLANTIC_STANDARD_TIME',
    'UTC_MINUS_3_ARGENTINA_TIME',
    'UTC_MINUS_2_SOUTH_GEORGIA_TIME',
    'UTC_MINUS_1_AZORES_TIME',

    -- UTC 0 (GMT)
    'UTC_0_GREENWICH_MEAN_TIME',
    'UTC_0_WESTERN_EUROPEAN_TIME',

    -- UTC +1 to UTC +5
    'UTC_1_CENTRAL_EUROPEAN_TIME',
    'UTC_1_WEST_AFRICA_TIME',
    'UTC_2_EASTERN_EUROPEAN_TIME',
    'UTC_2_CENTRAL_AFRICA_TIME',
    'UTC_3_MOSCOW_STANDARD_TIME',
    'UTC_3_EAST_AFRICA_TIME',
    'UTC_3_30_IRAN_STANDARD_TIME',
    'UTC_4_UNITED_ARAB_EMIRATES_TIME',
    'UTC_4_30_AFGHANISTAN_TIME',
    'UTC_5_PAKISTAN_STANDARD_TIME',
    'UTC_5_30_INDIA_STANDARD_TIME',
    'UTC_5_45_NEPAL_TIME',

    -- UTC +6 to UTC +10
    'UTC_6_BANGLADESH_STANDARD_TIME',
    'UTC_6_30_MYANMAR_TIME',
    'UTC_7_INDOCHINA_TIME',
    'UTC_8_CHINA_STANDARD_TIME',
    'UTC_8_30_NORTH_KOREA_TIME',
    'UTC_9_JAPAN_STANDARD_TIME',
    'UTC_9_30_AUSTRALIA_CENTRAL_STANDARD_TIME',
    'UTC_10_EASTERN_AUSTRALIA_STANDARD_TIME',

    -- UTC +11 to UTC +14
    'UTC_11_SOLOMON_ISLANDS_TIME',
    'UTC_12_NEW_ZEALAND_STANDARD_TIME',
    'UTC_12_FIJI_TIME',
    'UTC_13_TONGA_TIME',
    'UTC_14_KIRIBATI_TIME'
);


CREATE TABLE "event"(
    id                      VARCHAR(41) PRIMARY KEY,
    organizer               VARCHAR(255) NOT NULL,
    title                   VARCHAR(255) NOT NULL,
    description             TEXT NOT NULL,
    date_time               TIMESTAMP NOT NULL,
    time_zone               time_zone NOT NULL,
    location                TEXT NOT NULL,
    location_url            TEXT NOT NULL ,
    image_path              TEXT NOT NULL,
    category                event_category NOT NULL,
    status                  event_status NOT NULL,
    number_of_ticket        INT8 DEFAULT 0,
    max_ticket_per_user     INT DEFAULT 5,


    UNIQUE (
        title, description, date_time, time_zone,
        location, location_url, image_path, category, status,
        number_of_ticket, max_ticket_per_user
    )
);


CREATE TABLE events_type (
    id             VARCHAR(41) PRIMARY KEY,
    event_type     event_type NOT NULL,
    description    TEXT NOT NULL,

    UNIQUE (event_type)
);

CREATE TABLE has_type (
    id_event        VARCHAR(41) NOT NULL,
    id_events_type  VARCHAR(41) NOT NULL,

    FOREIGN KEY (id_event) REFERENCES "event"(id),
    FOREIGN KEY (id_events_type) REFERENCES "events_type"(id)
);

CREATE TABLE events_category (
    id                  VARCHAR(41) PRIMARY KEY,
    event_category      event_category NOT NULL,
    description         TEXT NOT NULL,
    id_event_type       VARCHAR(41) NOT NULL,

    UNIQUE (event_category),
    FOREIGN KEY (id_event_type) REFERENCES "events_type"(id)
);

CREATE TABLE "user" (
    email           	VARCHAR(255) PRIMARY KEY ,
	profile_img_path	TEXT NOT NULL DEFAULT '/user/no-profile-img-yet/',
    last_name       	VARCHAR(255) NOT NULL,
    first_name      	VARCHAR(255) NOT NULL,
    password        	TEXT NOT NULL,
    user_role       	user_role NOT NULL,
    status          	BOOLEAN DEFAULT false,

    UNIQUE (last_name, first_name, user_role)
);

CREATE TABLE tickets_type (
    id              VARCHAR(41) PRIMARY KEY,
    ticket_type     ticket_type NOT NULL,
	img_path		TEXT NOT NULL DEFAULT '/tickets/no-img-yet/',
    description     TEXT NOT NULL,

    UNIQUE (ticket_type)
);

CREATE TABLE ticket_price (
    id              VARCHAR(41) PRIMARY KEY,
    price           FLOAT DEFAULT 0.0,
    created_at      TIMESTAMP DEFAULT current_timestamp,
    max_number      INT8 DEFAULT 0,
    id_ticket_type  VARCHAR(41) NOT NULL,
    id_event        VARCHAR(41) NOT NULL,

    UNIQUE (price, id_ticket_type, id_event),
    FOREIGN KEY (id_ticket_type) REFERENCES "tickets_type"(id)
);

CREATE TABLE creates (
    user_email        VARCHAR(255) NOT NULL,
    id_event          VARCHAR(41) NOT NULL,
    created_at        TIMESTAMP DEFAULT current_timestamp,
    updated_at        TIMESTAMP DEFAULT current_timestamp,

    FOREIGN KEY (user_email) REFERENCES "user"(email),
    FOREIGN KEY (id_event) REFERENCES "event"(id)
);

CREATE TABLE payment_mode (
    id              VARCHAR(41) PRIMARY KEY,
    description     TEXT NOT NULL,
    payment_api_url TEXT NOT NULL,
    provider        payment_mode_provider NOT NULL,
    type            payment_mode_type NOT NULL,
    created_at      TIMESTAMP DEFAULT current_timestamp,
    updated_at      TIMESTAMP DEFAULT current_timestamp,
	logo_img_path	TEXT NOT NULL DEFAULT '/payment-mode/no-img-yet/',
    status          BOOLEAN DEFAULT false,

    UNIQUE (provider, type)
);

CREATE TABLE ticket (
    id                  VARCHAR(41) PRIMARY KEY,
    ticket_number       SERIAL8 NOT NULL,
    status	            BOOLEAN NOT NULL DEFAULT true,
    purchased_at        TIMESTAMP NOT NULL DEFAULT current_timestamp,
    qr_code_path        TEXT NOT NULL,
    payement_ref        TEXT NOT NULL,
    ticket_owner_name   TEXT NOT NULL,
    user_email          VARCHAR(255) NOT NULL,
    id_event            VARCHAR(41) NOT NULL,
    id_ticket_price     VARCHAR(41) NOT NULL,
    id_payment_mode     VARCHAR(41) NOT NULL,

    UNIQUE (ticket_number, id_event, id_ticket_price),
    FOREIGN KEY (user_email) REFERENCES "user"(email),
    FOREIGN KEY (id_event) REFERENCES "event"(id),
    FOREIGN KEY (id_ticket_price) REFERENCES "ticket_price"(id),
    FOREIGN KEY (id_payment_mode) REFERENCES "payment_mode"(id)
);

-- This SQL function will allow us to calculate the value of left ticket for a given event
--  If you notice, the event table doesn't have the attribute >> leftTicket << nor we don't have any information
-- about how many tickets left for a given event resource OR MORE IMPORTANT how many VIP, Bronze, Early Bird tickets left.
-- So, here we calculate the left ticket for a given ticket type.
-- @left_ticket = this is the left tickets of the subject_ticket_type of the particular event_id
CREATE OR REPLACE FUNCTION get_event_left_ticket_of_given_ticket_type (event_id VARCHAR, subject_ticket_type ticket_type)
RETURNS FLOAT AS $$
    DECLARE
        left_ticket		    INT8;
        ticket_price_id     VARCHAR(41);
        total_ticket	    INT8 := 0;
        sold_ticket 	    INT8 := 0;
BEGIN
    SELECT id INTO ticket_price_id
    FROM ticket_price
    WHERE id_event = event_id
      AND id_ticket_type = (SELECT tkt.id FROM tickets_type tkt WHERE tkt.ticket_type = subject_ticket_type);

    SELECT max_number INTO total_ticket
    FROM ticket_price tp
    WHERE tp.id = ticket_price_id;

    SELECT COALESCE(COUNT(*), 0) INTO sold_ticket
    FROM ticket
    WHERE id_ticket_price = ticket_price_id;

    left_ticket := total_ticket - sold_ticket;

    RETURN left_ticket;
END;
$$ LANGUAGE plpgsql;


-- As we discuss during our first meeting, we should also have the statistic of all ticket bought by an user
--and suggest him as priority the kind of event he used to buy in the past (like suggestions)
-- Explanation: In order to do that, we're going to give as result the top 5 of category the user has bought the most ticket with.
CREATE OR REPLACE FUNCTION get_the_top_5_category_of_user(target_user_email VARCHAR)
RETURNS JSONB[] AS $$
DECLARE
    result JSONB[];
BEGIN
    SELECT ARRAY(
        SELECT jsonb_build_object(
            'id', ec.id,
            'category', ec.event_category,
            'description', ec.description
        )
        FROM ticket t
        INNER JOIN event e ON t.id_event = e.id
        INNER JOIN events_category ec ON e.category = ec.event_category
        WHERE t.user_email = target_user_email
        GROUP BY ec.id, ec.event_category, ec.description
        ORDER BY COUNT(*) * 100.0 / NULLIF((SELECT COUNT(*) FROM ticket WHERE user_email = target_user_email), 0) DESC
        LIMIT 5
    ) INTO result;

    RETURN result;
END;
$$ LANGUAGE plpgsql;




-- DEFAULT INSERTION :
-- Insert into events_type
INSERT INTO events_type (id, event_type, description) VALUES
('$EvT-' || gen_random_uuid(), 'ENTERTAINMENT', 'Events focused on entertainment, including performances, shows, and fun activities for all ages.'),
('$EvT-' || gen_random_uuid(), 'MUSIC', 'Events centered around music, such as concerts, festivals, and live performances.'),
('$EvT-' || gen_random_uuid(), 'SPORTS', 'Events related to sports, including matches, tournaments, and fitness challenges.'),
('$EvT-' || gen_random_uuid(), 'THEATER_AND_SHOWS', 'Events featuring theatrical performances, plays, and live shows.'),
('$EvT-' || gen_random_uuid(), 'EDUCATION', 'Events focused on learning, including workshops, seminars, and educational courses.'),
('$EvT-' || gen_random_uuid(), 'CINEMA', 'Events related to movies, including screenings, premieres, and film festivals.'),
('$EvT-' || gen_random_uuid(), 'EXHIBITIONS_AND_CULTURE', 'Events showcasing art, culture, and exhibitions.'),
('$EvT-' || gen_random_uuid(), 'FESTIVALS_AND_FAIRS', 'Events celebrating festivals, fairs, and cultural gatherings.'),
('$EvT-' || gen_random_uuid(), 'PARTIES', 'Events focused on parties, celebrations, and social gatherings.'),
('$EvT-' || gen_random_uuid(), 'GAMING', 'Events related to gaming, including esports, tournaments, and gaming conventions.'),
('$EvT-' || gen_random_uuid(), 'BUSINESS', 'Events focused on business, networking, and professional development.'),
('$EvT-' || gen_random_uuid(), 'RELIGIOUS', 'Events related to religious and spiritual activities.'),
('$EvT-' || gen_random_uuid(), 'FOOD_AND_DRINK', 'Events centered around food, drinks, and culinary experiences.'),
('$EvT-' || gen_random_uuid(), 'CHARITY', 'Events focused on charity, fundraising, and community service.'),
('$EvT-' || gen_random_uuid(), 'TRAVEL', 'Events related to travel, adventure, and exploration.'),
('$EvT-' || gen_random_uuid(), 'FASHION', 'Events focused on fashion, design, and style.'),
('$EvT-' || gen_random_uuid(), 'TECHNOLOGY', 'Events centered around technology, innovation, and gadgets.'),
('$EvT-' || gen_random_uuid(), 'ENVIRONMENTAL', 'Events focused on environmental awareness and sustainability.'),
('$EvT-' || gen_random_uuid(), 'FAMILY_AND_KIDS', 'Events designed for families and children.'),
('$EvT-' || gen_random_uuid(), 'HEALTH_AND_WELLNESS', 'Events focused on health, wellness, and fitness.'),
('$EvT-' || gen_random_uuid(), 'LITERATURE_AND_BOOKS', 'Events centered around literature, books, and writing.'),
('$EvT-' || gen_random_uuid(), 'COMEDY', 'Events focused on comedy and humor.'),
('$EvT-' || gen_random_uuid(), 'NIGHTLIFE', 'Events related to nightlife, clubs, and evening entertainment.'),
('$EvT-' || gen_random_uuid(), 'OUTDOOR_AND_ADVENTURE', 'Events focused on outdoor activities and adventure.'),
('$EvT-' || gen_random_uuid(), 'COMMUNITY_AND_SOCIAL', 'Events focused on community building and social interaction.'),
('$EvT-' || gen_random_uuid(), 'HISTORY_AND_HERITAGE', 'Events centered around history, heritage, and cultural preservation.'),
('$EvT-' || gen_random_uuid(), 'SCIENCE_AND_TECH', 'Events focused on science, technology, and innovation.'),
('$EvT-' || gen_random_uuid(), 'ANIMALS_AND_NATURE', 'Events related to animals, nature, and wildlife.');


-- Insert into events_category :
-- Entertainment
INSERT INTO events_category (id, event_category, description, id_event_type) VALUES
('$EvC-' || gen_random_uuid(), 'MAGIC_AND_ILLUSIONS', 'Shows featuring magic tricks and illusions to amaze the audience.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
('$EvC-' || gen_random_uuid(), 'ARTISTIC_PERFORMANCES', 'Performances showcasing artistic expression, such as dance or theater.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
('$EvC-' || gen_random_uuid(), 'STREET_PERFORMANCES', 'Live performances held in public spaces, often interactive.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
('$EvC-' || gen_random_uuid(), 'CIRCUS_SHOW', 'Circus performances featuring acrobatics, clowns, and animal acts.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
('$EvC-' || gen_random_uuid(), 'VARIETY_SHOW', 'A show featuring a mix of performances, such as music, comedy, and magic.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
('$EvC-' || gen_random_uuid(), 'PUPPETRY', 'Shows featuring puppets as the main performers.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),
('$EvC-' || gen_random_uuid(), 'STORYTELLING', 'Events where storytellers narrate tales to an audience.', (SELECT id FROM events_type WHERE event_type = 'ENTERTAINMENT')),

-- Music
('$EvC-' || gen_random_uuid(), 'LIVE_CONCERT', 'A live performance by musicians or bands.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'MUSIC_FESTIVAL', 'A large-scale event featuring multiple music acts and performances.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'DJ_SET', 'A performance by a DJ playing electronic or dance music.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'ORCHESTRA', 'A performance by a large ensemble of classical musicians.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'OPERA_FESTIVAL', 'A festival dedicated to opera performances.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'ACOUSTIC_CONCERT', 'A live music performance featuring acoustic instruments.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'JAZZ_FESTIVAL', 'A festival dedicated to jazz music performances.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'ROCK_CONCERT', 'A live performance by rock bands or artists.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'CLASSICAL_MUSIC', 'Performances of classical music by orchestras or soloists.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),
('$EvC-' || gen_random_uuid(), 'KARAOKE_NIGHT', 'An event where participants sing along to recorded music.', (SELECT id FROM events_type WHERE event_type = 'MUSIC')),

-- Sports
('$EvC-' || gen_random_uuid(), 'MATCH', 'A competitive sports game between two teams or individuals.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'TOURNAMENT', 'A series of matches to determine the best team or player.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'MARATHON_AND_RACE', 'Long-distance running events for competition or charity.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'BATTLE', 'A competitive event showcasing physical or strategic skills.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'WATER_SPORTS', 'Events involving water-based activities, such as swimming or surfing.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'WINTER_SPORTS', 'Events involving winter activities, such as skiing or ice skating.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'CYCLING_RACE', 'Competitive cycling events on roads or tracks.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'TRIATHLON', 'A multi-sport event involving swimming, cycling, and running.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'EXTREME_SPORTS', 'Events featuring high-risk, adrenaline-pumping activities.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'EQUESTRIAN_EVENT', 'Events involving horse riding and equestrian sports.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
('$EvC-' || gen_random_uuid(), 'FITNESS_CHALLENGE', 'Events focused on physical fitness and endurance.', (SELECT id FROM events_type WHERE event_type = 'SPORTS')),
-- Theater & Shows
('$EvC-' || gen_random_uuid(), 'THEATER_PLAY', 'A live performance of a theatrical play.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'MUSICAL_COMEDY', 'A theatrical performance combining music and comedy.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'OPERA', 'A dramatic performance set to music, typically in a grand setting.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'BALLET_AND_DANCE', 'Performances featuring ballet or other forms of dance.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'IMPROV_THEATER', 'Theater performances where actors improvise scenes on the spot.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'PUPPET_SHOW', 'A theatrical performance using puppets as the main characters.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'CABARET', 'A lively performance combining music, dance, and comedy.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'MIME_SHOW', 'A performance using gestures and body movements without speech.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'ONE_MAN_SHOW', 'A theatrical performance featuring a single actor.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),
('$EvC-' || gen_random_uuid(), 'CHILDREN_THEATER', 'Theater performances designed for children.', (SELECT id FROM events_type WHERE event_type = 'THEATER_AND_SHOWS')),

-- Education
('$EvC-' || gen_random_uuid(), 'WORKSHOP', 'Interactive sessions focused on learning a specific skill.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'MASTERCLASS', 'Advanced classes taught by experts in a particular field.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'SEMINAR', 'Educational sessions featuring lectures and discussions.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'ONLINE_COURSE', 'Structured learning programs conducted online.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'LECTURE', 'A formal talk on a specific topic, often by an expert.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'HACKATHON', 'Events where participants collaborate to solve problems or create projects.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'CODING_BOOTCAMP', 'Intensive training programs focused on coding and programming.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),
('$EvC-' || gen_random_uuid(), 'LANGUAGE_EXCHANGE', 'Events where participants practice and learn new languages.', (SELECT id FROM events_type WHERE event_type = 'EDUCATION')),

-- Cinema
('$EvC-' || gen_random_uuid(), 'PREMIERE_SCREENING', 'The first public showing of a new movie.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'MOVIE_SCREENING', 'A showing of a film to an audience.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'OPEN_AIR_CINEMA', 'Outdoor movie screenings, often in a park or rooftop.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'FILM_FESTIVAL', 'A festival showcasing a selection of films.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'DOCUMENTARY_SCREENING', 'Screenings of documentary films.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'CLASSIC_MOVIE_SCREENING', 'Screenings of classic or vintage films.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'ANIME_SCREENING', 'Screenings of anime films or series.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'SHORT_FILM_FESTIVAL', 'A festival dedicated to short films.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),
('$EvC-' || gen_random_uuid(), 'SILENT_MOVIE_NIGHT', 'Screenings of silent films, often with live music accompaniment.', (SELECT id FROM events_type WHERE event_type = 'CINEMA')),

-- Exhibitions & Culture
('$EvC-' || gen_random_uuid(), 'ART_EXHIBITION', 'A display of artworks for public viewing.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'FAIR', 'A gathering for entertainment, commerce, or cultural activities.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'SCIENCE_EXHIBITION', 'Exhibitions showcasing scientific discoveries and innovations.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'HISTORY_EXHIBITION', 'Exhibitions focused on historical artifacts and events.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'CULTURAL_TOUR', 'Guided tours exploring cultural landmarks and traditions.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'PHOTOGRAPHY_EXHIBITION', 'Exhibitions showcasing photographic works.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'SCULPTURE_EXHIBITION', 'Exhibitions featuring sculptures and three-dimensional art.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'HISTORICAL_REENACTMENT', 'Events where historical events are recreated.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),
('$EvC-' || gen_random_uuid(), 'CULTURAL_WORKSHOP', 'Workshops focused on cultural practices and traditions.', (SELECT id FROM events_type WHERE event_type = 'EXHIBITIONS_AND_CULTURE')),

-- Festivals & Fairs
('$EvC-' || gen_random_uuid(), 'CULTURAL_FESTIVAL', 'Festivals celebrating cultural heritage and traditions.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'EXPO', 'Large-scale exhibitions showcasing products and innovations.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'CARNIVAL', 'Festivals featuring parades, costumes, and celebrations.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'GEEK_CONVENTION', 'Conventions for fans of geek culture, including comics and gaming.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'FOOD_FESTIVAL', 'Festivals celebrating food and culinary traditions.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'WINE_FESTIVAL', 'Festivals dedicated to wine tasting and appreciation.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'BOOK_FAIR', 'Events showcasing books and literature.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'COMIC_CON', 'Conventions for fans of comics, movies, and pop culture.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'HARVEST_FESTIVAL', 'Festivals celebrating the harvest season.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),
('$EvC-' || gen_random_uuid(), 'FLOWER_FESTIVAL', 'Festivals showcasing flowers and floral arrangements.', (SELECT id FROM events_type WHERE event_type = 'FESTIVALS_AND_FAIRS')),

-- Parties
('$EvC-' || gen_random_uuid(), 'NIGHTCLUB_PARTY', 'Parties held in nightclubs with music and dancing.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'POOL_PARTY', 'Outdoor parties held around a pool.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'COSTUME_PARTY', 'Parties where attendees wear costumes.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'BEACH_PARTY', 'Parties held on the beach, often with music and bonfires.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'THEMED_PARTY', 'Parties centered around a specific theme.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'GLOW_PARTY', 'Parties featuring neon lights and glow-in-the-dark decorations.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'SILENT_DISCO', 'Parties where attendees listen to music through headphones.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'ROOFTOP_PARTY', 'Parties held on rooftops, often with city views.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),
('$EvC-' || gen_random_uuid(), 'NEW_YEAR_EVE_PARTY', 'Parties celebrating the arrival of the new year.', (SELECT id FROM events_type WHERE event_type = 'PARTIES')),

-- Gaming
('$EvC-' || gen_random_uuid(), 'ESPORT_TOURNAMENT', 'Competitive gaming tournaments featuring professional players.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'GAMING_CONFERENCE', 'Conferences focused on gaming and the gaming industry.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'LAN_PARTY', 'Gaming events where participants connect via a local network.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'VIDEO_GAME_RELEASE', 'Events celebrating the release of new video games.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'BOARD_GAME_NIGHT', 'Events where participants play board games.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'COSPLAY_EVENT', 'Events where participants dress up as characters from games or movies.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'GAMING_MARATHON', 'Events where participants play games for an extended period.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),
('$EvC-' || gen_random_uuid(), 'VIRTUAL_REALITY_EXPO', 'Expos showcasing virtual reality technology and games.', (SELECT id FROM events_type WHERE event_type = 'GAMING')),

-- Business
('$EvC-' || gen_random_uuid(), 'PROFESSIONAL_FAIR', 'Fairs focused on career opportunities and professional development.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'BUSINESS_CONFERENCE', 'Conferences focused on business strategies and networking.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'STARTUP_PITCH_EVENT', 'Events where startups pitch their ideas to investors.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'CORPORATE_SOCIAL', 'Social events organized by companies for employees and clients.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'NETWORKING_EVENT', 'Events designed for professionals to connect and network.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'INVESTOR_PITCH', 'Events where entrepreneurs pitch their ideas to potential investors.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'TRADE_SHOW', 'Exhibitions showcasing products and services for a specific industry.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),
('$EvC-' || gen_random_uuid(), 'CORPORATE_TRAINING', 'Training sessions for employees to develop professional skills.', (SELECT id FROM events_type WHERE event_type = 'BUSINESS')),

-- Religious
('$EvC-' || gen_random_uuid(), 'RELIGIOUS_CEREMONY', 'Ceremonies and rituals associated with religious practices.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'SPIRITUAL_SONGS_AND_MUSIC', 'Events featuring spiritual or religious music.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'PILGRIMAGE', 'Journeys to sacred sites for religious purposes.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'FAITH_HEALING', 'Events focused on spiritual or faith-based healing practices.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'SPIRITUAL_RETREAT', 'Retreats focused on spiritual growth and reflection.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'PRAYER_MEETING', 'Gatherings for communal prayer and worship.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'RELIGIOUS_CONFERENCE', 'Conferences focused on religious teachings and discussions.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),
('$EvC-' || gen_random_uuid(), 'HOLY_DAY_CELEBRATION', 'Celebrations of religious holidays and holy days.', (SELECT id FROM events_type WHERE event_type = 'RELIGIOUS')),

-- Food and Drink
('$EvC-' || gen_random_uuid(), 'WINE_TASTING', 'Events where participants sample and learn about different wines.', (SELECT id FROM events_type WHERE event_type = 'FOOD_AND_DRINK')),
('$EvC-' || gen_random_uuid(), 'BEER_FESTIVAL', 'Festivals celebrating beer and brewing traditions.', (SELECT id FROM events_type WHERE event_type = 'FOOD_AND_DRINK')),
('$EvC-' || gen_random_uuid(), 'COFFEE_EXPO', 'Expos showcasing coffee and coffee-making techniques.', (SELECT id FROM events_type WHERE event_type = 'FOOD_AND_DRINK')),
('$EvC-' || gen_random_uuid(), 'COCKTAIL_WORKSHOP', 'Workshops focused on making and enjoying cocktails.', (SELECT id FROM events_type WHERE event_type = 'FOOD_AND_DRINK')),
('$EvC-' || gen_random_uuid(), 'CHOCOLATE_FESTIVAL', 'Festivals celebrating chocolate and chocolate-based treats.', (SELECT id FROM events_type WHERE event_type = 'FOOD_AND_DRINK')),
('$EvC-' || gen_random_uuid(), 'STREET_FOOD_FESTIVAL', 'Festivals featuring a variety of street food vendors.', (SELECT id FROM events_type WHERE event_type = 'FOOD_AND_DRINK')),

-- Charity
('$EvC-' || gen_random_uuid(), 'CHARITY_GALA', 'Formal events raising funds for charitable causes.', (SELECT id FROM events_type WHERE event_type = 'CHARITY')),
('$EvC-' || gen_random_uuid(), 'CHARITY_RUN', 'Running events organized to raise money for charity.', (SELECT id FROM events_type WHERE event_type = 'CHARITY')),
('$EvC-' || gen_random_uuid(), 'VOLUNTEER_EVENT', 'Events where participants volunteer for a cause.', (SELECT id FROM events_type WHERE event_type = 'CHARITY')),
('$EvC-' || gen_random_uuid(), 'FUNDRAISING_GALA', 'Gala events organized to raise funds for a specific cause.', (SELECT id FROM events_type WHERE event_type = 'CHARITY')),
('$EvC-' || gen_random_uuid(), 'CHARITY_AUCTION', 'Auctions where proceeds go to charitable organizations.', (SELECT id FROM events_type WHERE event_type = 'CHARITY')),
('$EvC-' || gen_random_uuid(), 'COMMUNITY_SERVICE', 'Events focused on community improvement and service.', (SELECT id FROM events_type WHERE event_type = 'CHARITY')),

-- Travel
('$EvC-' || gen_random_uuid(), 'TRAVEL_EXPO', 'Expos showcasing travel destinations and services.', (SELECT id FROM events_type WHERE event_type = 'TRAVEL')),
('$EvC-' || gen_random_uuid(), 'ADVENTURE_TOUR', 'Guided tours focused on adventure and exploration.', (SELECT id FROM events_type WHERE event_type = 'TRAVEL')),
('$EvC-' || gen_random_uuid(), 'EXOTIC_TRAVEL', 'Events promoting travel to exotic and unique destinations.', (SELECT id FROM events_type WHERE event_type = 'TRAVEL')),
('$EvC-' || gen_random_uuid(), 'CRUISE_EVENT', 'Events promoting cruise vacations and experiences.', (SELECT id FROM events_type WHERE event_type = 'TRAVEL')),
('$EvC-' || gen_random_uuid(), 'ADVENTURE_CAMP', 'Camps focused on outdoor and adventure activities.', (SELECT id FROM events_type WHERE event_type = 'TRAVEL')),
('$EvC-' || gen_random_uuid(), 'CULTURAL_IMMERSION', 'Events focused on experiencing and learning about different cultures.', (SELECT id FROM events_type WHERE event_type = 'TRAVEL')),

-- Fashion
('$EvC-' || gen_random_uuid(), 'FASHION_SHOW', 'Events showcasing the latest fashion trends and designs.', (SELECT id FROM events_type WHERE event_type = 'FASHION')),
('$EvC-' || gen_random_uuid(), 'FASHION_EXPO', 'Expos featuring fashion brands and designers.', (SELECT id FROM events_type WHERE event_type = 'FASHION')),
('$EvC-' || gen_random_uuid(), 'FASHION_WORKSHOP', 'Workshops focused on fashion design and styling.', (SELECT id FROM events_type WHERE event_type = 'FASHION')),
('$EvC-' || gen_random_uuid(), 'FASHION_AWARD', 'Award ceremonies celebrating achievements in fashion.', (SELECT id FROM events_type WHERE event_type = 'FASHION')),
('$EvC-' || gen_random_uuid(), 'POP_UP_STORE', 'Temporary retail stores showcasing fashion products.', (SELECT id FROM events_type WHERE event_type = 'FASHION')),
('$EvC-' || gen_random_uuid(), 'DESIGNER_SHOWCASE', 'Events showcasing the work of fashion designers.', (SELECT id FROM events_type WHERE event_type = 'FASHION')),

-- Technology
('$EvC-' || gen_random_uuid(), 'TECH_CONFERENCE', 'Conferences focused on technology and innovation.', (SELECT id FROM events_type WHERE event_type = 'TECHNOLOGY')),
('$EvC-' || gen_random_uuid(), 'GADGET_EXPO', 'Expos showcasing the latest gadgets and tech products.', (SELECT id FROM events_type WHERE event_type = 'TECHNOLOGY')),
('$EvC-' || gen_random_uuid(), 'INNOVATION_SUMMIT', 'Summits focused on technological innovation and trends.', (SELECT id FROM events_type WHERE event_type = 'TECHNOLOGY')),
('$EvC-' || gen_random_uuid(), 'AI_CONFERENCE', 'Conferences focused on artificial intelligence and machine learning.', (SELECT id FROM events_type WHERE event_type = 'TECHNOLOGY')),
('$EvC-' || gen_random_uuid(), 'ROBOTICS_EXPO', 'Expos showcasing robotics and automation technologies.', (SELECT id FROM events_type WHERE event_type = 'TECHNOLOGY')),
('$EvC-' || gen_random_uuid(), 'CYBERSECURITY_SUMMIT', 'Summits focused on cybersecurity and data protection.', (SELECT id FROM events_type WHERE event_type = 'TECHNOLOGY')),

-- Environmental
('$EvC-' || gen_random_uuid(), 'CLEANUP_EVENT', 'Events focused on cleaning up public spaces and the environment.', (SELECT id FROM events_type WHERE event_type = 'ENVIRONMENTAL')),
('$EvC-' || gen_random_uuid(), 'GREEN_CONFERENCE', 'Conferences focused on environmental sustainability.', (SELECT id FROM events_type WHERE event_type = 'ENVIRONMENTAL')),
('$EvC-' || gen_random_uuid(), 'ECO_FESTIVAL', 'Festivals celebrating eco-friendly practices and products.', (SELECT id FROM events_type WHERE event_type = 'ENVIRONMENTAL')),
('$EvC-' || gen_random_uuid(), 'TREE_PLANTING', 'Events focused on planting trees and improving green spaces.', (SELECT id FROM events_type WHERE event_type = 'ENVIRONMENTAL')),
('$EvC-' || gen_random_uuid(), 'SUSTAINABILITY_WORKSHOP', 'Workshops focused on sustainable living and practices.', (SELECT id FROM events_type WHERE event_type = 'ENVIRONMENTAL')),
('$EvC-' || gen_random_uuid(), 'ECO_FRIENDLY_MARKET', 'Markets featuring eco-friendly and sustainable products.', (SELECT id FROM events_type WHERE event_type = 'ENVIRONMENTAL')),

-- Family & Kids
('$EvC-' || gen_random_uuid(), 'KIDS_THEATER', 'Theater performances designed for children.', (SELECT id FROM events_type WHERE event_type = 'FAMILY_AND_KIDS')),
('$EvC-' || gen_random_uuid(), 'FAMILY_CARNIVAL', 'Carnivals designed for families and children.', (SELECT id FROM events_type WHERE event_type = 'FAMILY_AND_KIDS')),
('$EvC-' || gen_random_uuid(), 'CHILDREN_WORKSHOP', 'Workshops designed for children to learn and create.', (SELECT id FROM events_type WHERE event_type = 'FAMILY_AND_KIDS')),
('$EvC-' || gen_random_uuid(), 'AMUSEMENT_PARK_EVENT', 'Events held at amusement parks for families.', (SELECT id FROM events_type WHERE event_type = 'FAMILY_AND_KIDS')),
('$EvC-' || gen_random_uuid(), 'STORYTELLING_SESSION', 'Sessions where stories are told to children.', (SELECT id FROM events_type WHERE event_type = 'FAMILY_AND_KIDS')),

-- Health & Wellness
('$EvC-' || gen_random_uuid(), 'YOGA_CLASS', 'Classes focused on yoga and mindfulness.', (SELECT id FROM events_type WHERE event_type = 'HEALTH_AND_WELLNESS')),
('$EvC-' || gen_random_uuid(), 'MEDITATION_SESSION', 'Sessions focused on meditation and relaxation.', (SELECT id FROM events_type WHERE event_type = 'HEALTH_AND_WELLNESS')),
('$EvC-' || gen_random_uuid(), 'HEALTH_FITNESS_CHALLENGE', 'Events focused on physical fitness and challenges.', (SELECT id FROM events_type WHERE event_type = 'HEALTH_AND_WELLNESS')),
('$EvC-' || gen_random_uuid(), 'WELLNESS_RETREAT', 'Retreats focused on health and wellness.', (SELECT id FROM events_type WHERE event_type = 'HEALTH_AND_WELLNESS')),
('$EvC-' || gen_random_uuid(), 'HEALTH_FAIR', 'Fairs focused on health education and services.', (SELECT id FROM events_type WHERE event_type = 'HEALTH_AND_WELLNESS')),

-- Literature & Books
('$EvC-' || gen_random_uuid(), 'BOOK_SIGNING', 'Events where authors sign copies of their books.', (SELECT id FROM events_type WHERE event_type = 'LITERATURE_AND_BOOKS')),
('$EvC-' || gen_random_uuid(), 'AUTHOR_TALK', 'Talks and discussions with authors about their work.', (SELECT id FROM events_type WHERE event_type = 'LITERATURE_AND_BOOKS')),
('$EvC-' || gen_random_uuid(), 'POETRY_READING', 'Events where poets read their work aloud.', (SELECT id FROM events_type WHERE event_type = 'LITERATURE_AND_BOOKS')),
('$EvC-' || gen_random_uuid(), 'WRITING_WORKSHOP', 'Workshops focused on writing and storytelling.', (SELECT id FROM events_type WHERE event_type = 'LITERATURE_AND_BOOKS')),
('$EvC-' || gen_random_uuid(), 'BOOK_CLUB_MEETING', 'Meetings where book lovers discuss a selected book.', (SELECT id FROM events_type WHERE event_type = 'LITERATURE_AND_BOOKS')),

-- Comedy
('$EvC-' || gen_random_uuid(), 'STAND_UP_COMEDY', 'Live performances by comedians delivering humorous monologues.', (SELECT id FROM events_type WHERE event_type = 'COMEDY')),
('$EvC-' || gen_random_uuid(), 'IMPROV_COMEDY', 'Comedy performances where actors create scenes on the spot.', (SELECT id FROM events_type WHERE event_type = 'COMEDY')),
('$EvC-' || gen_random_uuid(), 'COMEDY_CLUB_NIGHT', 'Nights dedicated to comedy performances at a club.', (SELECT id FROM events_type WHERE event_type = 'COMEDY')),
('$EvC-' || gen_random_uuid(), 'SATIRE_SHOW', 'Shows featuring satirical humor and commentary.', (SELECT id FROM events_type WHERE event_type = 'COMEDY')),
('$EvC-' || gen_random_uuid(), 'COMEDY_FESTIVAL', 'Festivals dedicated to comedy performances.', (SELECT id FROM events_type WHERE event_type = 'COMEDY')),

-- Nightlife
('$EvC-' || gen_random_uuid(), 'NIGHTCLUB_EVENT', 'Events held at nightclubs featuring music and dancing.', (SELECT id FROM events_type WHERE event_type = 'NIGHTLIFE')),
('$EvC-' || gen_random_uuid(), 'LOUNGE_PARTY', 'Parties held in lounges with a relaxed atmosphere.', (SELECT id FROM events_type WHERE event_type = 'NIGHTLIFE')),
('$EvC-' || gen_random_uuid(), 'NIGHTLIFE_KARAOKE_NIGHT', 'Nights where participants sing along to recorded music.', (SELECT id FROM events_type WHERE event_type = 'NIGHTLIFE')),
('$EvC-' || gen_random_uuid(), 'THEMED_NIGHT', 'Nights with a specific theme for music and decorations.', (SELECT id FROM events_type WHERE event_type = 'NIGHTLIFE')),
('$EvC-' || gen_random_uuid(), 'AFTER_PARTY', 'Parties held after a main event, often late into the night.', (SELECT id FROM events_type WHERE event_type = 'NIGHTLIFE')),

-- Outdoor & Adventure
('$EvC-' || gen_random_uuid(), 'HIKING_TOUR', 'Guided tours focused on hiking and exploring nature.', (SELECT id FROM events_type WHERE event_type = 'OUTDOOR_AND_ADVENTURE')),
('$EvC-' || gen_random_uuid(), 'CAMPING_EVENT', 'Events focused on camping and outdoor activities.', (SELECT id FROM events_type WHERE event_type = 'OUTDOOR_AND_ADVENTURE')),
('$EvC-' || gen_random_uuid(), 'ROCK_CLIMBING', 'Events focused on rock climbing and bouldering.', (SELECT id FROM events_type WHERE event_type = 'OUTDOOR_AND_ADVENTURE')),
('$EvC-' || gen_random_uuid(), 'KAYAKING_TOUR', 'Guided tours focused on kayaking and water exploration.', (SELECT id FROM events_type WHERE event_type = 'OUTDOOR_AND_ADVENTURE')),
('$EvC-' || gen_random_uuid(), 'ADVENTURE_RACE', 'Races combining multiple outdoor activities.', (SELECT id FROM events_type WHERE event_type = 'OUTDOOR_AND_ADVENTURE')),

-- Community & Social
('$EvC-' || gen_random_uuid(), 'NEIGHBORHOOD_FAIR', 'Fairs celebrating local neighborhoods and communities.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_AND_SOCIAL')),
('$EvC-' || gen_random_uuid(), 'COMMUNITY_POTLUCK', 'Gatherings where community members share food and stories.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_AND_SOCIAL')),
('$EvC-' || gen_random_uuid(), 'VOLUNTEER_MEETUP', 'Meetups focused on volunteering and community service.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_AND_SOCIAL')),
('$EvC-' || gen_random_uuid(), 'SOCIAL_CLUB_EVENT', 'Events organized by social clubs for members.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_AND_SOCIAL')),
('$EvC-' || gen_random_uuid(), 'CULTURAL_EXCHANGE', 'Events focused on cultural exchange and understanding.', (SELECT id FROM events_type WHERE event_type = 'COMMUNITY_AND_SOCIAL')),

-- History & Heritage
('$EvC-' || gen_random_uuid(), 'HISTORICAL_TOUR', 'Guided tours focused on historical landmarks and sites.', (SELECT id FROM events_type WHERE event_type = 'HISTORY_AND_HERITAGE')),
('$EvC-' || gen_random_uuid(), 'REENACTMENT_EVENT', 'Events where historical events are reenacted.', (SELECT id FROM events_type WHERE event_type = 'HISTORY_AND_HERITAGE')),
('$EvC-' || gen_random_uuid(), 'HERITAGE_FESTIVAL', 'Festivals celebrating cultural heritage and traditions.', (SELECT id FROM events_type WHERE event_type = 'HISTORY_AND_HERITAGE')),
('$EvC-' || gen_random_uuid(), 'ARCHAEOLOGY_TALK', 'Talks and discussions focused on archaeology and history.', (SELECT id FROM events_type WHERE event_type = 'HISTORY_AND_HERITAGE')),
('$EvC-' || gen_random_uuid(), 'MUSEUM_NIGHT', 'Special events held at museums, often with extended hours.', (SELECT id FROM events_type WHERE event_type = 'HISTORY_AND_HERITAGE')),

-- Science & Tech
('$EvC-' || gen_random_uuid(), 'SCIENCE_FAIR', 'Fairs showcasing scientific projects and experiments.', (SELECT id FROM events_type WHERE event_type = 'SCIENCE_AND_TECH')),
('$EvC-' || gen_random_uuid(), 'TECH_DEMO', 'Demonstrations of new technology and innovations.', (SELECT id FROM events_type WHERE event_type = 'SCIENCE_AND_TECH')),
('$EvC-' || gen_random_uuid(), 'STEM_WORKSHOP', 'Workshops focused on science, technology, engineering, and math.', (SELECT id FROM events_type WHERE event_type = 'SCIENCE_AND_TECH')),
('$EvC-' || gen_random_uuid(), 'ASTRONOMY_NIGHT', 'Events focused on stargazing and astronomy.', (SELECT id FROM events_type WHERE event_type = 'SCIENCE_AND_TECH')),
('$EvC-' || gen_random_uuid(), 'INNOVATION_EXPO', 'Expos showcasing innovative technologies and ideas.', (SELECT id FROM events_type WHERE event_type = 'SCIENCE_AND_TECH')),

-- Animals & Nature
('$EvC-' || gen_random_uuid(), 'WILDLIFE_TOUR', 'Guided tours focused on observing wildlife in their natural habitat.', (SELECT id FROM events_type WHERE event_type = 'ANIMALS_AND_NATURE')),
('$EvC-' || gen_random_uuid(), 'PET_ADOPTION_EVENT', 'Events where pets are available for adoption.', (SELECT id FROM events_type WHERE event_type = 'ANIMALS_AND_NATURE')),
('$EvC-' || gen_random_uuid(), 'BIRD_WATCHING', 'Events focused on observing and identifying birds.', (SELECT id FROM events_type WHERE event_type = 'ANIMALS_AND_NATURE')),
('$EvC-' || gen_random_uuid(), 'NATURE_PHOTOGRAPHY', 'Events focused on capturing nature through photography.', (SELECT id FROM events_type WHERE event_type = 'ANIMALS_AND_NATURE')),
('$EvC-' || gen_random_uuid(), 'ANIMAL_SANCTUARY_VISIT', 'Visits to animal sanctuaries to learn about and interact with animals.', (SELECT id FROM events_type WHERE event_type = 'ANIMALS_AND_NATURE'));


-- Insert into tickets_type with descriptions
INSERT INTO tickets_type (id, ticket_type, description) VALUES
('$Tik-' || gen_random_uuid(), 'VIP', 'VIP tickets offer exclusive access, premium seating, and additional perks such as backstage passes or meet-and-greet opportunities.'),
('$Tik-' || gen_random_uuid(), 'STANDARD', 'Standard tickets provide general admission to the event with access to all main areas and activities.'),
('$Tik-' || gen_random_uuid(), 'EARLY_BIRD', 'Early bird tickets are discounted tickets available for a limited time, offering savings for those who purchase in advance.');


-- Insert into payment_mode with descriptions and API URLs
INSERT INTO payment_mode (id, description, payment_api_url, provider, type) VALUES
('$PmD-' || gen_random_uuid(), 'Mobile money payment via Mvola Madagascar.', 'https://api.mvola.mg/payment', 'MVOLA_MADAGASCAR', 'MOBILE_MONEY'),
('$PmD-' || gen_random_uuid(), 'Mobile money payment via Orange Money.', 'https://api.orange.money/payment', 'ORANGE_MONEY', 'MOBILE_MONEY'),
('$PmD-' || gen_random_uuid(), 'Mobile money payment via Airtel Money.', 'https://api.airtel.money/payment', 'AIRTEL_MONEY', 'MOBILE_MONEY'),
('$PmD-' || gen_random_uuid(), 'Payment via Visa credit or debit card.', 'https://api.visa.com/payment', 'VISA', 'VISA'),
('$PmD-' || gen_random_uuid(), 'Payment via Mastercard credit or debit card.', 'https://api.mastercard.com/payment', 'MASTERCARD', 'MASTERCARD'),
('$PmD-' || gen_random_uuid(), 'Payment via PayPal for online transactions.', 'https://api.paypal.com/payment', 'PAYPAL', 'PAYPAL'),
('$PmD-' || gen_random_uuid(), 'Payment via Stripe for online transactions.', 'https://api.stripe.com/payment', 'STRIPE', 'VISA'),
('$PmD-' || gen_random_uuid(), 'Payment via Stripe for online transactions.', 'https://api.stripe.com/payment', 'STRIPE', 'MASTERCARD'),
('$PmD-' || gen_random_uuid(), 'Payment via Google Pay for online and in-store transactions.', 'https://api.googlepay.com/payment', 'GOOGLE_PAY', 'VISA'),
('$PmD-' || gen_random_uuid(), 'Payment via Apple Pay for online and in-store transactions.', 'https://api.applepay.com/payment', 'APPLE_PAY', 'VISA'),
('$PmD-' || gen_random_uuid(), 'Payment via Bitcoin for cryptocurrency transactions.', 'https://api.bitcoin.com/payment', 'BITCOIN', 'CRYPTOCURRENCY'),
('$PmD-' || gen_random_uuid(), 'Payment via cash for in-person transactions.', 'N/A', 'CASH', 'CASH');
