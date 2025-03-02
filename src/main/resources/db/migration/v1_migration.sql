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
    'STAND_UP_COMEDY',
    'MAGIC_AND_ILLUSIONS',
    'ARTISTIC_PERFORMANCES',
    'STREET_PERFORMANCES',
    'COMEDY_FESTIVAL',
    'CIRCUS_SHOW',
    'VARIETY_SHOW',
    'IMPROV_COMEDY',
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
    'FITNESS_CHALLENGE',
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
    'IMPROV_COMEDY',
    'COMEDY_CLUB_NIGHT',
    'SATIRE_SHOW',
    'COMEDY_FESTIVAL',

    -- Nightlife
    'NIGHTCLUB_EVENT',
    'LOUNGE_PARTY',
    'KARAOKE_NIGHT',
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
    'BANK_TRANSFER',
    'CASH'
);

CREATE TYPE payment_mode_provider AS ENUM (
    'MVOLA_MADAGASCAR',
    'ORANGE_MONEY',
    'AIRTEL_MONEY',
    'PAYPAL',
    'STRIPE',
    'VISA',
    'MASTERCARD',
    'MTN_MOBILE_MONEY',
    'GOOGLE_PAY',
    'APPLE_PAY',
    'BITCOIN'
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
    category                event_category DEFAULT 'CONCERT',
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
    email           VARCHAR(255) PRIMARY KEY ,
    last_name       VARCHAR(255) NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    password        TEXT NOT NULL,
    user_role       user_role NOT NULL,
    status          BOOLEAN DEFAULT false,

    UNIQUE (last_name, first_name, user_role)
);

CREATE TABLE tickets_type (
    id              VARCHAR(41) PRIMARY KEY,
    ticket_type     ticket_type NOT NULL,
    description     TEXT NOT NULL,

    UNIQUE (ticket_type)
);

CREATE TABLE ticket_price (
    id              VARCHAR(41) PRIMARY KEY,
    price           FLOAT DEFAULT 0.0,
    created_at      TIMESTAMP DEFAULT current_timestamp,
    max_number      INT8 DEFAULT 0.0,
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
    status     BOOLEAN DEFAULT false,

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
    id_user             VARCHAR(41) NOT NULL,
    id_event            VARCHAR(41) NOT NULL,
    id_ticket_type      VARCHAR(41) NOT NULL,
    id_payment_mode    VARCHAR(41) NOT NULL,

    UNIQUE (ticket_number, id_event, id_ticket_type)
)
