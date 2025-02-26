CREATE ROLE tapakila_db_admin WITH LOGIN PASSWORD 'tapakila-db-admin-3bew';

DROP DATABASE IF EXISTS tapakila_db;
CREATE DATABASE tapakila_db;

\c tapakila_db;


GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES IN SCHEMA public TO tapakila_db_admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, UPDATE, INSERT, DELETE ON TABLES TO tapakila_db_admin;


GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO tapakila_db_admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT USAGE, SELECT ON SEQUENCES TO tapakila_db_admin;

CREATE TYPE event_category AS ENUM (
    -- Business & Professional  :
    'CONFERENCE',
    'WORKSHOP',
    'MEETUP',
    'NETWORKING',
    'SEMINAR',
    'SUMMIT',
    'EXPO',
    'TRADE_SHOW',
    'PANEL_DISCUSSION',

    -- Entertainment & Arts  :
    'CONCERT',
    'FESTIVAL',
    'THEATER',
    'COMEDY_SHOW',
    'CINEMA_SCREENING',
    'DANCE_SHOW',
    'ART_EXHIBITION',
    'LITERARY_EVENT',

    -- Sports & Outdoor  :
    'SPORTS',
    'TOURNAMENT',
    'MARATHON',
    'CYCLING_EVENT',
    'E_SPORTS',
    'ADVENTURE_RACE',
    'OUTDOOR_CAMPING',

    -- Tech & Innovation  :
    'HACKATHON',
    'CODING_BOOTCAMP',
    'STARTUP_PITCH',
    'WEBINAR',
    'TECH_FAIR',
    'PRODUCT_LAUNCH',

    -- Community & Social  :
    'CHARITY_EVENT',
    'FUNDRAISER',
    'RELIGIOUS_GATHERING',
    'POLITICAL_RALLY',
    'PRIDE_EVENT',

    -- Education & Training  :
    'COLLEGE_FAIR',
    'GRADUATION_CEREMONY',
    'TUTORING_SESSION',
    'PUBLIC_LECTURE',

    -- Food & Drinks  :
    'FOOD_FESTIVAL',
    'WINE_TASTING',
    'COOKING_WORKSHOP',

    -- Health & Wellness  :
    'YOGA_RETREAT',
    'FITNESS_BOOTCAMP',
    'MENTAL_HEALTH_TALK',
    'WELLNESS_CONFERENCE'
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


CREATE TABLE "event" (
    id                      VARCHAR(41) PRIMARY KEY,
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

CREATE TABLE "user" (
    email           VARCHAR(255) PRIMARY KEY ,
    last_name       VARCHAR(255) NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    password        TEXT NOT NULL,
    user_role       user_role NOT NULL,
    is_disabled     BOOLEAN DEFAULT false,

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
    id_user           VARCHAR(41) NOT NULL,
    id_event          VARCHAR(41) NOT NULL,
    created_at        TIMESTAMP DEFAULT current_timestamp,
    updated_at        TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE payment_mode (
    id              VARCHAR(41) PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    provider        payment_mode_provider NOT NULL,
    type            payment_mode_type NOT NULL,
    created_at      TIMESTAMP DEFAULT current_timestamp,
    updated_at      TIMESTAMP DEFAULT current_timestamp,
    is_disabled     BOOLEAN DEFAULT false,

    UNIQUE (provider, type)
);

CREATE TABLE ticket (
    id                  VARCHAR(41) PRIMARY KEY,
    ticket_number       SERIAL8 NOT NULL,
    is_enabled          BOOLEAN NOT NULL DEFAULT true,
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
