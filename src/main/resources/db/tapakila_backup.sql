--
-- PostgreSQL database dump
--

-- Dumped from database version 15.12 (Debian 15.12-0+deb12u2)
-- Dumped by pg_dump version 15.12 (Debian 15.12-0+deb12u2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: currency; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.currency AS ENUM (
    'MGA',
    'DOLLAR',
    'EURO',
    'LIVRE STERLING'
);


ALTER TYPE public.currency OWNER TO postgres;

--
-- Name: event_category; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.event_category AS ENUM (
    'MAGIC_AND_ILLUSIONS',
    'ARTISTIC_PERFORMANCES',
    'STREET_PERFORMANCES',
    'CIRCUS_SHOW',
    'VARIETY_SHOW',
    'PUPPETRY',
    'STORYTELLING',
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
    'WORKSHOP',
    'MASTERCLASS',
    'SEMINAR',
    'ONLINE_COURSE',
    'LECTURE',
    'HACKATHON',
    'CODING_BOOTCAMP',
    'LANGUAGE_EXCHANGE',
    'PREMIERE_SCREENING',
    'MOVIE_SCREENING',
    'OPEN_AIR_CINEMA',
    'FILM_FESTIVAL',
    'DOCUMENTARY_SCREENING',
    'CLASSIC_MOVIE_SCREENING',
    'ANIME_SCREENING',
    'SHORT_FILM_FESTIVAL',
    'SILENT_MOVIE_NIGHT',
    'ART_EXHIBITION',
    'FAIR',
    'SCIENCE_EXHIBITION',
    'HISTORY_EXHIBITION',
    'CULTURAL_TOUR',
    'PHOTOGRAPHY_EXHIBITION',
    'SCULPTURE_EXHIBITION',
    'HISTORICAL_REENACTMENT',
    'CULTURAL_WORKSHOP',
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
    'NIGHTCLUB_PARTY',
    'POOL_PARTY',
    'COSTUME_PARTY',
    'BEACH_PARTY',
    'THEMED_PARTY',
    'GLOW_PARTY',
    'SILENT_DISCO',
    'ROOFTOP_PARTY',
    'NEW_YEAR_EVE_PARTY',
    'ESPORT_TOURNAMENT',
    'GAMING_CONFERENCE',
    'LAN_PARTY',
    'VIDEO_GAME_RELEASE',
    'BOARD_GAME_NIGHT',
    'COSPLAY_EVENT',
    'GAMING_MARATHON',
    'VIRTUAL_REALITY_EXPO',
    'PROFESSIONAL_FAIR',
    'BUSINESS_CONFERENCE',
    'STARTUP_PITCH_EVENT',
    'CORPORATE_SOCIAL',
    'NETWORKING_EVENT',
    'INVESTOR_PITCH',
    'TRADE_SHOW',
    'CORPORATE_TRAINING',
    'RELIGIOUS_CEREMONY',
    'SPIRITUAL_SONGS_AND_MUSIC',
    'PILGRIMAGE',
    'FAITH_HEALING',
    'SPIRITUAL_RETREAT',
    'PRAYER_MEETING',
    'RELIGIOUS_CONFERENCE',
    'HOLY_DAY_CELEBRATION',
    'WINE_TASTING',
    'BEER_FESTIVAL',
    'COFFEE_EXPO',
    'COCKTAIL_WORKSHOP',
    'CHOCOLATE_FESTIVAL',
    'STREET_FOOD_FESTIVAL',
    'CHARITY_GALA',
    'CHARITY_RUN',
    'VOLUNTEER_EVENT',
    'FUNDRAISING_GALA',
    'CHARITY_AUCTION',
    'COMMUNITY_SERVICE',
    'TRAVEL_EXPO',
    'ADVENTURE_TOUR',
    'EXOTIC_TRAVEL',
    'CRUISE_EVENT',
    'ADVENTURE_CAMP',
    'CULTURAL_IMMERSION',
    'FASHION_SHOW',
    'FASHION_EXPO',
    'FASHION_WORKSHOP',
    'FASHION_AWARD',
    'POP_UP_STORE',
    'DESIGNER_SHOWCASE',
    'TECH_CONFERENCE',
    'GADGET_EXPO',
    'INNOVATION_SUMMIT',
    'AI_CONFERENCE',
    'ROBOTICS_EXPO',
    'CYBERSECURITY_SUMMIT',
    'CLEANUP_EVENT',
    'GREEN_CONFERENCE',
    'ECO_FESTIVAL',
    'TREE_PLANTING',
    'SUSTAINABILITY_WORKSHOP',
    'ECO_FRIENDLY_MARKET',
    'KIDS_THEATER',
    'FAMILY_CARNIVAL',
    'CHILDREN_WORKSHOP',
    'AMUSEMENT_PARK_EVENT',
    'STORYTELLING_SESSION',
    'YOGA_CLASS',
    'MEDITATION_SESSION',
    'HEALTH_FITNESS_CHALLENGE',
    'WELLNESS_RETREAT',
    'HEALTH_FAIR',
    'BOOK_SIGNING',
    'AUTHOR_TALK',
    'POETRY_READING',
    'WRITING_WORKSHOP',
    'BOOK_CLUB_MEETING',
    'STAND_UP_COMEDY',
    'COMEDY_CLUB_NIGHT',
    'SATIRE_SHOW',
    'IMPROV_COMEDY',
    'COMEDY_FESTIVAL',
    'NIGHTCLUB_EVENT',
    'LOUNGE_PARTY',
    'NIGHTLIFE_KARAOKE_NIGHT',
    'THEMED_NIGHT',
    'AFTER_PARTY',
    'HIKING_TOUR',
    'CAMPING_EVENT',
    'ROCK_CLIMBING',
    'KAYAKING_TOUR',
    'ADVENTURE_RACE',
    'NEIGHBORHOOD_FAIR',
    'COMMUNITY_POTLUCK',
    'VOLUNTEER_MEETUP',
    'SOCIAL_CLUB_EVENT',
    'CULTURAL_EXCHANGE',
    'HISTORICAL_TOUR',
    'REENACTMENT_EVENT',
    'HERITAGE_FESTIVAL',
    'ARCHAEOLOGY_TALK',
    'MUSEUM_NIGHT',
    'SCIENCE_FAIR',
    'TECH_DEMO',
    'STEM_WORKSHOP',
    'ASTRONOMY_NIGHT',
    'INNOVATION_EXPO',
    'WILDLIFE_TOUR',
    'PET_ADOPTION_EVENT',
    'BIRD_WATCHING',
    'NATURE_PHOTOGRAPHY',
    'ANIMAL_SANCTUARY_VISIT'
);


ALTER TYPE public.event_category OWNER TO postgres;

--
-- Name: event_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.event_status AS ENUM (
    'DRAFT',
    'PUBLISHED',
    'SCHEDULED',
    'ONGOING',
    'COMPLETED',
    'CANCELED'
);


ALTER TYPE public.event_status OWNER TO postgres;

--
-- Name: event_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.event_type AS ENUM (
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


ALTER TYPE public.event_type OWNER TO postgres;

--
-- Name: payment_mode_provider; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.payment_mode_provider AS ENUM (
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
);


ALTER TYPE public.payment_mode_provider OWNER TO postgres;

--
-- Name: payment_mode_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.payment_mode_type AS ENUM (
    'MOBILE_MONEY',
    'VISA',
    'MASTERCARD',
    'PAYPAL',
    'CRYPTOCURRENCY',
    'BITCOIN',
    'CASH'
);


ALTER TYPE public.payment_mode_type OWNER TO postgres;

--
-- Name: ticket_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.ticket_type AS ENUM (
    'VIP',
    'STANDARD',
    'EARLY_BIRD'
);


ALTER TYPE public.ticket_type OWNER TO postgres;

--
-- Name: time_zone; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.time_zone AS ENUM (
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
    'UTC_0_GREENWICH_MEAN_TIME',
    'UTC_0_WESTERN_EUROPEAN_TIME',
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
    'UTC_6_BANGLADESH_STANDARD_TIME',
    'UTC_6_30_MYANMAR_TIME',
    'UTC_7_INDOCHINA_TIME',
    'UTC_8_CHINA_STANDARD_TIME',
    'UTC_8_30_NORTH_KOREA_TIME',
    'UTC_9_JAPAN_STANDARD_TIME',
    'UTC_9_30_AUSTRALIA_CENTRAL_STANDARD_TIME',
    'UTC_10_EASTERN_AUSTRALIA_STANDARD_TIME',
    'UTC_11_SOLOMON_ISLANDS_TIME',
    'UTC_12_NEW_ZEALAND_STANDARD_TIME',
    'UTC_12_FIJI_TIME',
    'UTC_13_TONGA_TIME',
    'UTC_14_KIRIBATI_TIME'
);


ALTER TYPE public.time_zone OWNER TO postgres;

--
-- Name: user_role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.user_role AS ENUM (
    'ADMIN',
    'USER'
);


ALTER TYPE public.user_role OWNER TO postgres;

--
-- Name: get_event_left_ticket_of_given_ticket_type_at_a_given_date(character varying, public.ticket_type, date, date); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_event_left_ticket_of_given_ticket_type_at_a_given_date(event_id character varying, subject_ticket_type public.ticket_type, intervaldatestart date, intervaldateend date) RETURNS bigint
    LANGUAGE plpgsql
    AS $$
DECLARE
    result              int8;
    ticket_price_id     VARCHAR(41);
    total_ticket	    INT8 := 0;
    sold_ticket 	    INT8 := 0;
    ticketCreatedAt     TIMESTAMP;
BEGIN

    SELECT id INTO ticket_price_id
    FROM ticket_price
    WHERE id_event = event_id
      AND id_ticket_type = (SELECT tkt.id FROM tickets_type tkt WHERE tkt.ticket_type = subject_ticket_type);

    SELECT created_at INTO ticketCreatedAt
    FROM ticket_price tp
    WHERE tp.id = ticket_price_id;

    IF intervalDateStart IS NULL THEN
        intervalDateStart := ticketCreatedAt;
    END IF;

    SELECT max_number INTO total_ticket
    FROM ticket_price tp
    WHERE tp.id = ticket_price_id;

    SELECT COUNT(*) INTO sold_ticket
    FROM ticket t
    WHERE id_ticket_price = ticket_price_id
      AND purchased_at BETWEEN intervalDateStart::timestamp AND intervalDateEnd::timestamp;

    result := total_ticket - sold_ticket;

    RETURN result;
END;
$$;


ALTER FUNCTION public.get_event_left_ticket_of_given_ticket_type_at_a_given_date(event_id character varying, subject_ticket_type public.ticket_type, intervaldatestart date, intervaldateend date) OWNER TO postgres;

--
-- Name: get_the_top_5_category_of_user(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_the_top_5_category_of_user(target_user_email character varying) RETURNS jsonb[]
    LANGUAGE plpgsql
    AS $$
DECLARE
    result jsonb[];
BEGIN
    SELECT COALESCE(
        JSONB_AGG(
            DISTINCT JSONB_BUILD_OBJECT(
                'category_id', ec.id,
                'event_category', ec.event_category,
                'description', ec.description
            )
        ) FILTER (WHERE ec.id IS NOT NULL),
        '[]'::jsonb
    )
    INTO result
    FROM events_category ec
    INNER JOIN event e ON ec.event_category = e.category
    INNER JOIN ticket t ON e.id = t.id_event
    WHERE t.user_email = target_user_email
    GROUP BY ec.id
    LIMIT 5;

    RETURN result;
END;
$$;


ALTER FUNCTION public.get_the_top_5_category_of_user(target_user_email character varying) OWNER TO postgres;

--
-- Name: is_activation_active(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.is_activation_active(activation_id character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
    DECLARE
        expiration  TIMESTAMP;

    BEGIN
        SELECT expired_at INTO expiration
        FROM account_activation WHERE id = activation_id;

        RETURN current_timestamp < expiration;
    END;
$$;


ALTER FUNCTION public.is_activation_active(activation_id character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: access_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.access_token (
    access_token text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expires_at timestamp without time zone NOT NULL,
    is_valid boolean DEFAULT false,
    user_email character varying(255) NOT NULL
);


ALTER TABLE public.access_token OWNER TO postgres;

--
-- Name: account_activation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_activation (
    id character varying(41) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expired_at timestamp without time zone GENERATED ALWAYS AS ((created_at + '00:10:00'::interval)) STORED,
    activated_at timestamp without time zone,
    user_email character varying(255) NOT NULL,
    code character varying(9)
);


ALTER TABLE public.account_activation OWNER TO postgres;

--
-- Name: creates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.creates (
    user_email character varying(255) NOT NULL,
    id_event character varying(41) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.creates OWNER TO postgres;

--
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id character varying(41) NOT NULL,
    organizer character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    description text NOT NULL,
    date_time timestamp without time zone NOT NULL,
    time_zone public.time_zone NOT NULL,
    location text NOT NULL,
    location_url text NOT NULL,
    image_path text NOT NULL,
    category public.event_category NOT NULL,
    status public.event_status NOT NULL,
    number_of_ticket bigint DEFAULT 0,
    max_ticket_per_user integer DEFAULT 5
);


ALTER TABLE public.event OWNER TO postgres;

--
-- Name: events_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.events_category (
    id character varying(41) NOT NULL,
    event_category public.event_category NOT NULL,
    description text NOT NULL,
    id_event_type character varying(41) NOT NULL
);


ALTER TABLE public.events_category OWNER TO postgres;

--
-- Name: events_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.events_type (
    id character varying(41) NOT NULL,
    event_type public.event_type NOT NULL,
    description text NOT NULL
);


ALTER TABLE public.events_type OWNER TO postgres;

--
-- Name: has_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.has_type (
    id_event character varying(41) NOT NULL,
    id_events_type character varying(41) NOT NULL
);


ALTER TABLE public.has_type OWNER TO postgres;

--
-- Name: payment_mode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_mode (
    id character varying(41) NOT NULL,
    description text NOT NULL,
    payment_api_url text NOT NULL,
    provider public.payment_mode_provider NOT NULL,
    type public.payment_mode_type NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    logo_img_path text DEFAULT '/payment-mode/no-img-yet/'::text NOT NULL,
    status boolean DEFAULT false
);


ALTER TABLE public.payment_mode OWNER TO postgres;

--
-- Name: refresh_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.refresh_token (
    refresh_token character varying(41) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expires_at timestamp without time zone NOT NULL,
    is_valid boolean DEFAULT false,
    user_email character varying(255) NOT NULL
);


ALTER TABLE public.refresh_token OWNER TO postgres;

--
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket (
    id character varying(41) NOT NULL,
    ticket_number bigint NOT NULL,
    status boolean DEFAULT true NOT NULL,
    purchased_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    qr_code_path text NOT NULL,
    payement_ref text NOT NULL,
    ticket_owner_name text NOT NULL,
    user_email character varying(255) NOT NULL,
    id_ticket_price character varying(41) NOT NULL,
    id_payment_mode character varying(41) NOT NULL
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- Name: ticket_price; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket_price (
    id character varying(41) NOT NULL,
    price double precision DEFAULT 0.0,
    created_at timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP,
    max_number bigint DEFAULT 0.0,
    id_ticket_type character varying(41) NOT NULL,
    id_event character varying(41) NOT NULL,
    currency public.currency DEFAULT 'MGA'::public.currency
);


ALTER TABLE public.ticket_price OWNER TO postgres;

--
-- Name: ticket_ticket_number_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ticket_ticket_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ticket_ticket_number_seq OWNER TO postgres;

--
-- Name: ticket_ticket_number_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ticket_ticket_number_seq OWNED BY public.ticket.ticket_number;


--
-- Name: tickets_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets_type (
    id character varying(41) NOT NULL,
    ticket_type public.ticket_type NOT NULL,
    img_path text DEFAULT '/tickets/no-img-yet/'::text NOT NULL,
    description text NOT NULL
);


ALTER TABLE public.tickets_type OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    email character varying(255) NOT NULL,
    profile_img_path text DEFAULT '/user/no-profile-img-yet/'::text NOT NULL,
    last_name character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    password text NOT NULL,
    user_role public.user_role NOT NULL,
    is_active boolean DEFAULT false,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: ticket ticket_number; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket ALTER COLUMN ticket_number SET DEFAULT nextval('public.ticket_ticket_number_seq'::regclass);


--
-- Data for Name: access_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.access_token (access_token, created_at, expires_at, is_valid, user_email) FROM stdin;
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXBha2lsYS5ub3JlcGx5QGdtYWlsLmNvbSIsImlhdCI6MTc0MjQwNjgzNiwiZXhwIjoxNzQyNTc5NjM2LCJjcmVhdGVkQXQiOiIyMDI1LTAzLTE5VDE3OjUzOjU2LjI2MTM2MDU1MSIsInJvbGUiOiJBRE1JTiIsImZpcnN0TmFtZSI6IlJhemFmaW5kcmF0ZWxvIiwiYWNjb3VudENyZWF0aW9uRGF0ZSI6IjIwMjUtMDMtMTlUMTc6MTY6MDYuODQyNjg4IiwiZW1haWwiOiJ0YXBha2lsYS5ub3JlcGx5QGdtYWlsLmNvbSIsImxhc3ROYW1lIjoiQWJlZ8OgIiwiZXhwaXJlc0F0IjoiMjAyNS0wMy0yMVQxNzo1Mzo1Ni4yNjEzNjA1NTEifQ.5XTtYiChWI4LoM1qwnxg6XkAyRA-cZ5osnNISfw_wIedwv22z4x4hzpbtc8UR_uh6kzfT2dhg6VIfN2Rgj-3uw	2025-03-19 17:53:56.261361	2025-03-21 17:53:56.261361	f	tapakila.noreply@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXBha2lsYS5ub3JlcGx5QGdtYWlsLmNvbSIsImlhdCI6MTc0MjQ3ODMxOSwiZXhwIjoxNzQyNjUxMTE5LCJlbWFpbCI6InRhcGFraWxhLm5vcmVwbHlAZ21haWwuY29tIiwibGFzdE5hbWUiOiJBYmVnw6AiLCJleHBpcmVzQXQiOiIyMDI1LTAzLTIyVDEzOjQ1OjE5LjE3MTg4MzQ4NCIsImNyZWF0ZWRBdCI6IjIwMjUtMDMtMjBUMTM6NDU6MTkuMTcxODgzNDg0Iiwicm9sZSI6IkFETUlOIiwiZmlyc3ROYW1lIjoiUmF6YWZpbmRyYXRlbG8iLCJhY2NvdW50Q3JlYXRpb25EYXRlIjoiMjAyNS0wMy0xOVQxNzoxNjowNi44NDI2ODgifQ.17IdwcRs6H1AP-L80fQ8qwY8D2iXlfSm0jOVDihfsfSOGOkqJHygQkyKNPojgQxKSVB6lTsujfdOjAubOE7YhQ	2025-03-20 13:45:19.171883	2025-03-22 13:45:19.171883	f	tapakila.noreply@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXBha2lsYS5ub3JlcGx5QGdtYWlsLmNvbSIsImlhdCI6MTc0MjQ3OTgxMCwiZXhwIjoxNzQyNjUyNjEwLCJsYXN0TmFtZSI6IkFiZWfDoCIsImV4cGlyZXNBdCI6IjIwMjUtMDMtMjJUMTQ6MTA6MTAuODI3NDMyMzk5IiwiY3JlYXRlZEF0IjoiMjAyNS0wMy0yMFQxNDoxMDoxMC44Mjc0MzIzOTkiLCJyb2xlIjoiQURNSU4iLCJmaXJzdE5hbWUiOiJSYXphZmluZHJhdGVsbyIsImFjY291bnRDcmVhdGlvbkRhdGUiOiIyMDI1LTAzLTE5VDE3OjE2OjA2Ljg0MjY4OCIsImVtYWlsIjoidGFwYWtpbGEubm9yZXBseUBnbWFpbC5jb20ifQ._efOUYTJBShDmnJVgZEc39mO3CeujYUgixMv4TM_U7D-e9I_ZM4Ee9FCm7H9HbJgRf2cu5LT66FPwhFMOwSK6g	2025-03-20 14:10:10.827432	2025-03-22 14:10:10.827432	t	tapakila.noreply@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXBha2lsYS5ub3JlcGx5QGdtYWlsLmNvbSIsImlhdCI6MTc0MjQ4MDg5MiwiZXhwIjoxNzQyNjUzNjkyLCJyb2xlIjoiQURNSU4iLCJjcmVhdGVkQXQiOiIyMDI1LTAzLTIwVDE0OjI4OjEyLjI3NzY3MDg3MSIsImV4cGlyZXNBdCI6IjIwMjUtMDMtMjJUMTQ6Mjg6MTIuMjc3NjcwODcxIiwibGFzdE5hbWUiOiJBYmVnw6AiLCJlbWFpbCI6InRhcGFraWxhLm5vcmVwbHlAZ21haWwuY29tIiwiYWNjb3VudENyZWF0aW9uRGF0ZSI6IjIwMjUtMDMtMTlUMTc6MTY6MDYuODQyNjg4IiwiZmlyc3ROYW1lIjoiUmF6YWZpbmRyYXRlbG8ifQ.Y9gk2YqzKG7jIqCZ1DVi7980qn-DqQtwX2-R_yD6aO663wcDhzXjYW1i_vxFDb8Xd8Ll0kt19HX8PxutlNrF5A	2025-03-20 14:28:12.277671	2025-03-22 14:28:12.277671	t	tapakila.noreply@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLnJhemFmaW5kcmF0ZWxvQGdtYWlsLmNvbSIsImlhdCI6MTc0MjQ4MzQzNSwiZXhwIjoxNzQyNjU2MjM1LCJjcmVhdGVkQXQiOiIyMDI1LTAzLTIwVDE1OjEwOjM1LjAwMzcwNDAxOCIsInJvbGUiOiJVU0VSIiwiZmlyc3ROYW1lIjoiUmF6YWZpbmRyYXRlbG8iLCJhY2NvdW50Q3JlYXRpb25EYXRlIjoiMjAyNS0wMy0yMFQxNTowODo1NC4wNzgxMjgiLCJlbWFpbCI6ImEucmF6YWZpbmRyYXRlbG9AZ21haWwuY29tIiwibGFzdE5hbWUiOiJBYmVnw6AiLCJleHBpcmVzQXQiOiIyMDI1LTAzLTIyVDE1OjEwOjM1LjAwMzcwNDAxOCJ9.SYg1txz52i9tihPNVvTg1xXcdtW0TGLdxSOcHOuHY37_uGtrFRJCQv8SWlDc1gRcDGtXNM96RvN7TwN2gRkswA	2025-03-20 15:10:35.003704	2025-03-22 15:10:35.003704	t	a.razafindratelo@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhLnJhemFmaW5kcmF0ZWxvQGdtYWlsLmNvbSIsImlhdCI6MTc0MjY0NTc1NCwiZXhwIjoxNzQyODE4NTU0LCJmaXJzdE5hbWUiOiJSYXphZmluZHJhdGVsbyIsInJvbGUiOiJVU0VSIiwiY3JlYXRlZEF0IjoiMjAyNS0wMy0yMlQxMjoxNTo1NC42ODU1MzcxMTQiLCJleHBpcmVzQXQiOiIyMDI1LTAzLTI0VDEyOjE1OjU0LjY4NTUzNzExNCIsImxhc3ROYW1lIjoiQWJlZ8OgIiwiZW1haWwiOiJhLnJhemFmaW5kcmF0ZWxvQGdtYWlsLmNvbSIsImFjY291bnRDcmVhdGlvbkRhdGUiOiIyMDI1LTAzLTIwVDE1OjA4OjU0LjA3ODEyOCJ9.f2TogBsRL4Yo4srsN0HuHY5nbkai_cdtQFiFYJqw0V3-yk04csUxN2Bmkp-p8XH7Q1Y6gRHyO4vIStsIiVxAMA	2025-03-22 12:15:54.685537	2025-03-24 12:15:54.685537	t	a.razafindratelo@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0YXBha2lsYS5ub3JlcGx5QGdtYWlsLmNvbSIsImlhdCI6MTc0MjkwMzcxOCwiZXhwIjoxNzQzMDc2NTE4LCJhY2NvdW50Q3JlYXRpb25EYXRlIjoiMjAyNS0wMy0xOVQxNzoxNjowNi44NDI2ODgiLCJmaXJzdE5hbWUiOiJSYXphZmluZHJhdGVsbyIsInJvbGUiOiJBRE1JTiIsImNyZWF0ZWRBdCI6IjIwMjUtMDMtMjVUMTE6NTU6MTguODI5ODEwODU4IiwiZXhwaXJlc0F0IjoiMjAyNS0wMy0yN1QxMTo1NToxOC44Mjk4MTA4NTgiLCJsYXN0TmFtZSI6IkFiZWfDoCIsImVtYWlsIjoidGFwYWtpbGEubm9yZXBseUBnbWFpbC5jb20ifQ.sohaujf7xY5tNC2-j-VGVBsZBw2cWVwO3MvqjTF-Nvod7kX1Zevs-d6OmozjPdxjHALduIufS97-8XMMzRKJ1A	2025-03-25 11:55:18.829811	2025-03-27 11:55:18.829811	t	tapakila.noreply@gmail.com
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWkudHNhbnRhLjNAZ21haWwuY29tIiwiaWF0IjoxNzQzNjExNDA0LCJleHAiOjE3NDM3ODQyMDQsImNyZWF0ZWRBdCI6IjIwMjUtMDQtMDJUMTY6MzA6MDQuNTk2MDk4NjQzIiwiZXhwaXJlc0F0IjoiMjAyNS0wNC0wNFQxNjozMDowNC41OTYwOTg2NDMiLCJsYXN0TmFtZSI6ImpvaG4iLCJlbWFpbCI6ImhlaS50c2FudGEuM0BnbWFpbC5jb20iLCJhY2NvdW50Q3JlYXRpb25EYXRlIjoiMjAyNS0wNC0wMlQxNjoyNTo0OC41MjU5MzYiLCJmaXJzdE5hbWUiOiJkb2UiLCJyb2xlIjoiQURNSU4ifQ.UP-jEvDoIvy5p9fRTAUmzWAE6q1onAc5AIi2MDc4wpX5SFOayZGZxDHpnNGkh9SqhGCCgWseuWZweaLSps2CBg	2025-04-02 16:30:04.596099	2025-04-04 16:30:04.596099	t	hei.tsanta.3@gmail.com
\.


--
-- Data for Name: account_activation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account_activation (id, created_at, activated_at, user_email, code) FROM stdin;
$AcA-fe9be7e4-8a93-4d2b-8890-2c7cfb1c7afe	2025-03-19 17:16:12.052883	2025-03-19 17:17:31.317671	tapakila.noreply@gmail.com	013663749
$AcA-0a15e072-52bb-4fef-8bed-90126ba82385	2025-03-20 15:08:59.342944	2025-03-20 15:10:14.032988	a.razafindratelo@gmail.com	831114668
$AcA-a5aee2f6-dfa6-478a-b2f7-8ec0caa44779	2025-04-02 16:25:54.988641	2025-04-02 16:28:25.44016	hei.tsanta.3@gmail.com	513778771
\.


--
-- Data for Name: creates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.creates (user_email, id_event, created_at, updated_at) FROM stdin;
admin@example.com	$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	2025-03-02 23:52:53.293997	2025-03-02 23:52:53.293997
admin@example.com	$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2	2025-03-02 23:52:53.293997	2025-03-02 23:52:53.293997
admin@example.com	$Evt-5be48f4c-790a-4206-835f-ba39210c54f7	2025-03-02 23:52:53.293997	2025-03-02 23:52:53.293997
admin@example.com	$Evt-d0de138a-6c43-45c5-86f9-4772b8f80acc	2025-03-02 23:52:53.293997	2025-03-02 23:52:53.293997
tapakila.noreply@gmail.com	$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-300effb5-2665-4a6f-b285-46242f51d545	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-62eca069-e832-49b5-a37c-889ef52b2b30	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-c3caa18d-2087-43da-b933-7a34b915287b	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-d62731db-1fae-484e-9780-4b7e804179f4	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-4f4c6994-cc24-40fe-85ed-6fc738d43999	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-363a87d1-46ac-4002-8282-d80eca467558	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-feabf759-7c25-41ee-a724-81b3df34b760	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-abf8cfd1-9fd4-40b8-9544-99527995334c	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-63e96074-ee58-4688-af06-f914ba7d6a55	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-47a7235a-faec-492a-94cb-3300238ce56e	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
tapakila.noreply@gmail.com	$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a	2025-04-02 12:03:53.684822	2025-04-02 12:03:53.684822
\.


--
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event (id, organizer, title, description, date_time, time_zone, location, location_url, image_path, category, status, number_of_ticket, max_ticket_per_user) FROM stdin;
$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2	Farthotel	Night Party	Join us for an unforgettable night party at Hotel Carlton! Featuring special performances and a star-studded guest list.	2025-02-28 20:00:00	UTC_1_CENTRAL_EUROPEAN_TIME	Hotel Carlton	https://maps.example.com/hotel-carlton	src/main/resources/static/assets/image/event/no_image.png	NIGHTCLUB_PARTY	PUBLISHED	500	2
$Evt-5be48f4c-790a-4206-835f-ba39210c54f7	Studio Showcase	Annual Studio Showcase	A night filled with mesmerizing performances, exquisite music, and a live orchestra. Join us for an unforgettable evening!	2025-04-15 19:00:00	UTC_1_CENTRAL_EUROPEAN_TIME	La Scène D'ivandry	https://maps.example.com/la-scene-divandry	src/main/resources/static/assets/image/event/no_image.png	OPERA	PUBLISHED	300	2
$Evt-d0de138a-6c43-45c5-86f9-4772b8f80acc	Theater Play Organizers	Theater Play	A time for you to discover the magic of theater! Join us for an unforgettable evening of drama and performance.	2025-03-24 19:00:00	UTC_3_EAST_AFRICA_TIME	Tranompokonolona Analakely	https://maps.example.com/tranompokonolona-analakely	src/main/resources/static/assets/image/event/no_image.png	THEATER_PLAY	PUBLISHED	200	4
$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	Music Fest Organizers	Annual Summer Music Festival	This is a random test to update event description	2024-07-15 18:00:00	UTC_1_CENTRAL_EUROPEAN_TIME	Central Park, New York City	https://maps.example.com/central-park	src/main/resources/static/assets/image/event/no_image.png	MUSIC_FESTIVAL	PUBLISHED	10000	4
$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495	RDJ	Tyla	Tyla concert	2025-01-18 15:00:00	UTC_3_EAST_AFRICA_TIME	Stade Barea Mahamasina	https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495/Afrobeat1.png	LIVE_CONCERT	COMPLETED	10000	4
$Evt-300effb5-2665-4a6f-b285-46242f51d545	Cinepax	Wicked	Wicked movie screening	2025-04-09 21:00:00	UTC_3_EAST_AFRICA_TIME	Cinepax tana water front	https://www.google.com/maps/place/Cinepax+Madagascar/@-18.8919645,47.5236875,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080b2dd1c9e5b:0xc7e33226f589c666!8m2!3d-18.8919696!4d47.5262624!16s%2Fg%2F11hd5b8098?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-300effb5-2665-4a6f-b285-46242f51d545/Screening1.png	MOVIE_SCREENING	PUBLISHED	1000	4
$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	Canal Olympia	Zootopia 2	Zootopia 2 movie screening	2025-04-10 10:00:00	UTC_3_EAST_AFRICA_TIME	Canal Olympia Andohotapenaka	https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29/Screening2.png	MOVIE_SCREENING	PUBLISHED	1000	4
$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a	Salford	Valentine	Valentine day party	2025-02-14 19:00:00	UTC_3_EAST_AFRICA_TIME	La Balancoire Ivandry	https://www.google.com/maps/place/La+Balan%C3%A7oire/@-18.873963,47.5152788,666m/data=!3m1!1e3!4m6!3m5!1s0x21f081b978c8c7cd:0xa963de6a09f427e1!8m2!3d-18.8739689!4d47.5180846!16s%2Fg%2F11stvs3cjs?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a/Nightclub5.png	NIGHTCLUB_PARTY	PUBLISHED	500	4
$Evt-62eca069-e832-49b5-a37c-889ef52b2b30	Salford	Night Party	Night party at Carlton	2025-04-12 20:00:00	UTC_3_EAST_AFRICA_TIME	Hotel Carlton Anosy	https://www.google.com/maps/place/Hotel+Carlton/@-18.9148433,47.515274,631m/data=!3m1!1e3!4m9!3m8!1s0x21f07e0e0292d259:0x810bb6406b272064!5m2!4m1!1i2!8m2!3d-18.9148484!4d47.5178489!16s%2Fm%2F0wfjbn0?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-62eca069-e832-49b5-a37c-889ef52b2b30/Nightclub1.png	NIGHTCLUB_PARTY	PUBLISHED	250	4
$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81	Salford	Young Party	Night party for young people	2025-04-14 20:00:00	UTC_3_EAST_AFRICA_TIME	La city Ivandry	https://www.google.com/maps/place/La+city+Ivandry/@-18.8751665,47.5170449,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f081cee5e14a9f:0x61982eb38c1d40e!8m2!3d-18.8751716!4d47.5196198!16s%2Fg%2F11g0qlrdz2?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81/Nightclub6.png	NIGHTCLUB_PARTY	PUBLISHED	400	4
$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3	Salford	Bob tobias	Bob tobias business conference	2025-04-07 10:00:00	UTC_3_EAST_AFRICA_TIME	Sehatra Maitso Antsahamanitra	https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116045,47.5195971,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3/Conference1.png	BUSINESS_CONFERENCE	PUBLISHED	300	4
$Evt-4f4c6994-cc24-40fe-85ed-6fc738d43999	Salford	Public Speaking	Public Speaking workshop	2025-04-22 08:00:00	UTC_3_EAST_AFRICA_TIME	Canal Olympia	https://www.google.com/maps/place/CanalOlympia+Iarivo/@-18.8885043,47.4868805,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810ca3467cbb:0x74e92108284649df!8m2!3d-18.8885094!4d47.4894554!16s%2Fg%2F11fmdmgnpg?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	events-posters/Education/Workshops/Workshop1.png	WORKSHOP	PUBLISHED	500	4
$Evt-abf8cfd1-9fd4-40b8-9544-99527995334c	Salford	Esperanza	Esperanza Spalding show	2025-04-11 20:00:00	UTC_3_EAST_AFRICA_TIME	La city Ivandry	https://www.google.com/maps/place/La+city+Ivandry/@-18.8751665,47.5170449,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f081cee5e14a9f:0x61982eb38c1d40e!8m2!3d-18.8751716!4d47.5196198!16s%2Fg%2F11g0qlrdz2?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	events-posters/Music/ConcertLive/jazz/jazz1.png	LIVE_CONCERT	PUBLISHED	600	4
$Evt-63e96074-ee58-4688-af06-f914ba7d6a55	Salford	Esperanza	Esperanza Spalding show	2025-04-13 20:00:00	UTC_3_EAST_AFRICA_TIME	Stade Barea Mahamasina	https://www.google.com/maps/place/Stade+Barea+Mahamasina/@-18.9194574,47.5233782,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a5c0d79cf:0xdc643e778a74b324!8m2!3d-18.9194625!4d47.5259531!16zL20vMGJwd3Bu?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	events-posters/Music/ConcertLive/Jazz/Jazz1.png	LIVE_CONCERT	PUBLISHED	800	4
$Evt-d62731db-1fae-484e-9780-4b7e804179f4	Salford	Easter	Night of Easter celebration	2025-04-19 09:00:00	UTC_3_EAST_AFRICA_TIME	Coliseum Antsonjombe	https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-d62731db-1fae-484e-9780-4b7e804179f4/Spiritual-song1.png	SPIRITUAL_SONGS_AND_MUSIC	PUBLISHED	500	4
$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c	Salford	E-sport	E-sport Tournament	2025-04-16 09:00:00	UTC_3_EAST_AFRICA_TIME	Redzone Ankorondrano	https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8480807,47.4146735,10098m/data=!3m1!1e3!4m10!1m2!2m1!1sredzone!3m6!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!15sCgdyZWR6b25lkgEPY293b3JraW5nX3NwYWNl4AEA!16s%2Fg%2F11mxp3kzxv?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c/Tournament1.png	ESPORT_TOURNAMENT	PUBLISHED	200	4
$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705	Salford	Web Development	Web Development masterclass	2025-04-26 08:00:00	UTC_3_EAST_AFRICA_TIME	Sweety Ampefiloha	https://www.google.com/maps/place/Sweety+Space/@-18.9147992,47.5137866,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07fa249d02497:0xed31c5ed5ae25591!8m2!3d-18.9148043!4d47.5163615!16s%2Fg%2F11ht58tvm7?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705/MasterClass1.png	MASTERCLASS	PUBLISHED	200	4
$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff	Salford	Kids Robotic	Kids Robotic masterclass	2025-04-19 08:00:00	UTC_3_EAST_AFRICA_TIME	Canal Olympia	https://www.google.com/maps/place/RedZone+Ankorondrano/@-18.8735794,47.5181853,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f0810053f21f45:0x58472c0fcc57e4e9!8m2!3d-18.8735845!4d47.5207602!16s%2Fg%2F11mxp3kzxv?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff/Workshop1.png	MASTERCLASS	PUBLISHED	400	4
$Evt-363a87d1-46ac-4002-8282-d80eca467558	Salford	Boxing	Boxing battle	2025-04-28 08:00:00	UTC_3_EAST_AFRICA_TIME	Palais des Sports Mahamasina	https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205828,47.5244095,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-363a87d1-46ac-4002-8282-d80eca467558/Battle1.png	BATTLE	PUBLISHED	1000	4
$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad	Salford	Basketball match	Basketball match in Mahamasina	2025-04-29 08:00:00	UTC_3_EAST_AFRICA_TIME	Palais des Sports Mahamasina	https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205828,47.5244095,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad/Match1.png	MATCH	PUBLISHED	1000	4
$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea	Salford	Swimming	Swimming Tournament	2025-04-30 08:00:00	UTC_3_EAST_AFRICA_TIME	Acsa Ambohidahy	https://www.google.com/maps/place/Palais+de+la+Culture+et+des+Sports/@-18.9205828,47.5244095,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e0a8558d041:0xe0c64c428dd75a7b!8m2!3d-18.9205879!4d47.5269844!16s%2Fg%2F1tlnssxm?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea/Tournament1.png	TOURNAMENT	PUBLISHED	300	4
$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff	Salford	Ballet	Ballet show	2025-04-23 08:00:00	UTC_3_EAST_AFRICA_TIME	Ccesca Antanimena	https://www.google.com/maps/place/Centre+Culturel+ESCA/@-18.8974177,47.5195832,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080aea42995b9:0xeb776d49b50eb79!8m2!3d-18.8974228!4d47.5221581!16s%2Fg%2F1thbn5gq?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff/Ballet1.png	BALLET_AND_DANCE	PUBLISHED	500	4
$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f	Salford	Theater	Theater show	2025-04-25 08:00:00	UTC_3_EAST_AFRICA_TIME	Tranompokonolona Analakely	https://www.google.com/maps/place/Tranompokonolona+Analakely/@-18.9086197,47.5243946,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07f864bb6fc6d:0x435f4407f9d482d8!8m2!3d-18.9086248!4d47.5269695!16s%2Fg%2F11fltw3dxt?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f/Theater1.png	THEATER_PLAY	PUBLISHED	100	4
$Evt-feabf759-7c25-41ee-a724-81b3df34b760	Salford	Big Mj	Big Mj show	2025-04-10 20:00:00	UTC_3_EAST_AFRICA_TIME	Sehatra Maitso Antsahamanitra	https://www.google.com/maps/place/Theatre+de+Verdure+Antsahamanitra/@-18.9116045,47.5195971,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f07e05f8555555:0xec58ce3595942a1e!8m2!3d-18.9116096!4d47.522172!16s%2Fg%2F11kj906rp6?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-feabf759-7c25-41ee-a724-81b3df34b760/Afrobeat2.png	LIVE_CONCERT	PUBLISHED	500	4
$Evt-47a7235a-faec-492a-94cb-3300238ce56e	Salford	Taylor Swift	Taylor Swift concert	2025-04-15 21:00:00	UTC_3_EAST_AFRICA_TIME	Cci Ivato	https://www.google.com/maps/place/CCI+IVATO/@-18.8142774,47.4723262,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f081890f4829cd:0x88d7f3c627aab6b4!8m2!3d-18.8142825!4d47.4749011!16s%2Fg%2F11g7296xxn?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-47a7235a-faec-492a-94cb-3300238ce56e/Pop2.png	LIVE_CONCERT	PUBLISHED	1000	4
$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449	Salford	Linkin Park	Linkin Park concert	2025-04-17 19:00:00	UTC_3_EAST_AFRICA_TIME	Coliseum Antsonjombe	https://www.google.com/maps/place/Coliseum-Antsonjombe/@-6.968244,-20.3991844,10845516m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449/Rock1.png	LIVE_CONCERT	PUBLISHED	800	4
$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a	Salford	Drew Feig	Drew Feig concert	2025-04-18 18:00:00	UTC_3_EAST_AFRICA_TIME	Ccesca Antanimena	https://www.google.com/maps/place/Centre+Culturel+ESCA/@-18.8974177,47.5195832,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080aea42995b9:0xeb776d49b50eb79!8m2!3d-18.8974228!4d47.5221581!16s%2Fg%2F1thbn5gq?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a/Rock2.png	LIVE_CONCERT	PUBLISHED	500	4
$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844	RDJ	Weeknd	The weeknd concert	2025-03-29 18:00:00	UTC_3_EAST_AFRICA_TIME	Coliseum Antsonjombe	https://www.google.com/maps/place/Coliseum-Antsonjombe/@-18.8699731,47.5416333,631m/data=!3m1!1e3!4m6!3m5!1s0x21f086df7a0fcf57:0x4896ba0a2af4e751!8m2!3d-18.8699782!4d47.5442082!16s%2Fg%2F1td80nwq?entry=ttu&g_ep=EgoyMDI1MDMyNS4xIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844/Pop4.png	LIVE_CONCERT	PUBLISHED	10000	8
$Evt-c3caa18d-2087-43da-b933-7a34b915287b	Salford	Daniel Gallego	Religious Conference wit Daniel Gallego	2025-04-06 10:00:00	UTC_3_EAST_AFRICA_TIME	CCESCA Antanimena	https://www.google.com/maps/place/Centre+Culturel+ESCA/@-18.8974177,47.5195832,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f080aea42995b9:0xeb776d49b50eb79!8m2!3d-18.8974228!4d47.5221581!16s%2Fg%2F1thbn5gq?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-c3caa18d-2087-43da-b933-7a34b915287b/Religious-ceremony1.png	RELIGIOUS_CONFERENCE	PUBLISHED	400	4
$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9	Salford	Opera	Opera show	2025-04-24 08:00:00	UTC_3_EAST_AFRICA_TIME	Cci Ivato	https://www.google.com/maps/place/CCI+IVATO/@-18.8142774,47.4723262,631m/data=!3m2!1e3!4b1!4m6!3m5!1s0x21f081890f4829cd:0x88d7f3c627aab6b4!8m2!3d-18.8142825!4d47.4749011!16s%2Fg%2F11g7296xxn?entry=ttu&g_ep=EgoyMDI1MDMzMC4wIKXMDSoASAFQAw%3D%3D	src/main/resources/static/assets/image/event/$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9/Opera1.png	OPERA	PUBLISHED	1000	4
\.


--
-- Data for Name: events_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.events_category (id, event_category, description, id_event_type) FROM stdin;
$EvC-27da0527-d28d-4caf-b5c5-a8a222816bcc	MAGIC_AND_ILLUSIONS	Shows featuring magic tricks and illusions to amaze the audience.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-8576e803-2282-482d-9793-52d63081cdba	ARTISTIC_PERFORMANCES	Performances showcasing artistic expression, such as dance or theater.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-f15f1437-c0a1-46dc-8d9a-221134ef624b	STREET_PERFORMANCES	Live performances held in public spaces, often interactive.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-64a39257-a474-45c6-a7f9-1c50f101d012	CIRCUS_SHOW	Circus performances featuring acrobatics, clowns, and animal acts.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-70adfe0f-f3ec-4c53-a1c1-8124bd468c32	VARIETY_SHOW	A show featuring a mix of performances, such as music, comedy, and magic.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-8eee4ac0-da28-40f0-ae53-6b5d08a4447f	PUPPETRY	Shows featuring puppets as the main performers.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-47bf72c6-d9f6-4edc-abde-1d2a0e1263ab	STORYTELLING	Events where storytellers narrate tales to an audience.	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$EvC-5d5c6e3e-b093-4499-af17-8828a041e708	LIVE_CONCERT	A live performance by musicians or bands.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-6a4df93a-cb21-446d-92f8-fa394eb215a1	MUSIC_FESTIVAL	A large-scale event featuring multiple music acts and performances.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-43480c80-d922-4eb4-8180-ea05cf3994fb	DJ_SET	A performance by a DJ playing electronic or dance music.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-324736c7-eb99-4051-9d12-4b30a4598079	ORCHESTRA	A performance by a large ensemble of classical musicians.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-3a23ed4f-ad76-4435-949f-8e19b96610e7	OPERA_FESTIVAL	A festival dedicated to opera performances.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-63d51a90-899a-45b2-b86f-e25bfd8fde85	ACOUSTIC_CONCERT	A live music performance featuring acoustic instruments.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-c8c1ab6a-aac7-49a2-b223-6936c34fa2cd	JAZZ_FESTIVAL	A festival dedicated to jazz music performances.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-db234ea2-287d-4888-afde-e26defe2a5f5	ROCK_CONCERT	A live performance by rock bands or artists.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-c0e15a9d-8ec6-4d6b-9311-205fb26b41b1	CLASSICAL_MUSIC	Performances of classical music by orchestras or soloists.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-40bc8136-1318-4bac-84e3-53f385adf530	KARAOKE_NIGHT	An event where participants sing along to recorded music.	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$EvC-9089fe55-f4d4-438e-842f-2de456046975	MATCH	A competitive sports game between two teams or individuals.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-0e6fc5ee-c7f6-4fa6-ac42-ff7d713db1ef	TOURNAMENT	A series of matches to determine the best team or player.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-54c18dbe-3610-48db-8ba8-1fa7f0f19807	MARATHON_AND_RACE	Long-distance running events for competition or charity.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-debc50ed-9f5b-4c38-8009-1deb95511113	BATTLE	A competitive event showcasing physical or strategic skills.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-74446b17-d636-4558-af87-8f1a21c75c5e	WATER_SPORTS	Events involving water-based activities, such as swimming or surfing.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-b5740dc1-5433-46fa-b0b8-32752dc76bc4	WINTER_SPORTS	Events involving winter activities, such as skiing or ice skating.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-0e9579ba-e332-4397-a30a-ec00695749ad	CYCLING_RACE	Competitive cycling events on roads or tracks.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-97282e0a-7479-4d4e-ad19-effb45833d1e	TRIATHLON	A multi-sport event involving swimming, cycling, and running.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-60764c16-bb46-4981-91be-07fe36f7a4e4	EXTREME_SPORTS	Events featuring high-risk, adrenaline-pumping activities.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-37ea3cbd-0f44-4645-9dc7-538632e95806	EQUESTRIAN_EVENT	Events involving horse riding and equestrian sports.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-ee40001a-9e3b-4eb1-8823-ec330fe76d7a	FITNESS_CHALLENGE	Events focused on physical fitness and endurance.	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$EvC-8928c8db-38df-4f17-b6e0-20e0ecc6c5f3	THEATER_PLAY	A live performance of a theatrical play.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-a7552d37-a465-4919-822e-4b2dc539083f	MUSICAL_COMEDY	A theatrical performance combining music and comedy.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-8d287c71-0e60-4e32-94b4-8f6b6ac9324b	OPERA	A dramatic performance set to music, typically in a grand setting.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-5e5440cc-3099-4fd1-9ff8-cd320f22e3f9	BALLET_AND_DANCE	Performances featuring ballet or other forms of dance.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-d92cf756-7ee9-41af-a18c-8820ef2cd644	IMPROV_THEATER	Theater performances where actors improvise scenes on the spot.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-ffddf45d-c63c-4b65-bbec-1d05792f5158	PUPPET_SHOW	A theatrical performance using puppets as the main characters.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-3f2050bb-91fa-43cd-8f63-96a5acb0ea5f	CABARET	A lively performance combining music, dance, and comedy.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-65a70859-bfc0-4cec-8a0f-3c901d72be1d	MIME_SHOW	A performance using gestures and body movements without speech.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-9be63ba0-8b6c-451e-8e7a-8ec577d33695	ONE_MAN_SHOW	A theatrical performance featuring a single actor.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-a411cddf-22a5-4303-b56c-243983c1ba6d	CHILDREN_THEATER	Theater performances designed for children.	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$EvC-097230cc-2830-435c-8a86-c936d9b57e1f	WORKSHOP	Interactive sessions focused on learning a specific skill.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-d363058b-dcb6-46e9-b77c-5365bbac8547	MASTERCLASS	Advanced classes taught by experts in a particular field.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-f172e10c-f238-4718-a8b7-d66648fd8ca1	SEMINAR	Educational sessions featuring lectures and discussions.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-d9415269-db8a-4cbe-99a3-e8bc7438a9bb	ONLINE_COURSE	Structured learning programs conducted online.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-661746a7-6f11-43a5-b137-322a700b929c	LECTURE	A formal talk on a specific topic, often by an expert.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-9c15bbe3-1f37-47bf-88df-56a8cb8c64cb	HACKATHON	Events where participants collaborate to solve problems or create projects.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-4fe1510d-97a1-48cd-b6f3-058f276fc211	CODING_BOOTCAMP	Intensive training programs focused on coding and programming.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-6c50ab7e-895f-4db0-a621-7d6c557ae9d3	LANGUAGE_EXCHANGE	Events where participants practice and learn new languages.	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$EvC-e5593221-b82b-49cb-9a29-2d617ad94ec3	PREMIERE_SCREENING	The first public showing of a new movie.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-6ffe8e79-4035-4749-90ec-977b43d33e9a	MOVIE_SCREENING	A showing of a film to an audience.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-e5fabdae-4823-40c6-9d42-bf3aaff40f57	OPEN_AIR_CINEMA	Outdoor movie screenings, often in a park or rooftop.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-b9c5ddc8-d594-4dab-8736-844a6e5977eb	FILM_FESTIVAL	A festival showcasing a selection of films.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-06e26baa-09e0-4b02-881e-9be82c29594c	DOCUMENTARY_SCREENING	Screenings of documentary films.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-d36a97a1-1311-4b43-b024-07073048b289	CLASSIC_MOVIE_SCREENING	Screenings of classic or vintage films.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-d489d6d4-29cd-416e-b725-651333c5645d	ANIME_SCREENING	Screenings of anime films or series.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-9bf39686-ba11-467f-97f6-05afa2803b86	SHORT_FILM_FESTIVAL	A festival dedicated to short films.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-a9d2253c-152c-40ff-b57c-b4aa54633371	SILENT_MOVIE_NIGHT	Screenings of silent films, often with live music accompaniment.	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$EvC-d5bcc630-1a2f-48cb-b40f-5adb06f73287	ART_EXHIBITION	A display of artworks for public viewing.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-a4eff2c4-821f-4dc0-a84e-f422afb8a608	FAIR	A gathering for entertainment, commerce, or cultural activities.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-df42abac-24b0-42ab-afa6-0a6fb89d76bf	SCIENCE_EXHIBITION	Exhibitions showcasing scientific discoveries and innovations.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-63314c46-e270-4b17-9b4c-a8fa260a1514	HISTORY_EXHIBITION	Exhibitions focused on historical artifacts and events.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-dcbf1aa9-8993-4dad-8ba0-584bb96dcc92	CULTURAL_TOUR	Guided tours exploring cultural landmarks and traditions.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-c5e61b45-a6c5-45d5-927d-85de9969e55d	PHOTOGRAPHY_EXHIBITION	Exhibitions showcasing photographic works.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-5c58d5cd-26af-44ba-8e68-3c83c2c98886	SCULPTURE_EXHIBITION	Exhibitions featuring sculptures and three-dimensional art.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-9046bb84-373b-496b-a375-482a837d5ea1	HISTORICAL_REENACTMENT	Events where historical events are recreated.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-14b6f8f9-5350-4a1e-9ef8-8f74ecf26a8d	CULTURAL_WORKSHOP	Workshops focused on cultural practices and traditions.	$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80
$EvC-c44bc94e-2e93-43f7-b588-907eb8455c1f	CULTURAL_FESTIVAL	Festivals celebrating cultural heritage and traditions.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-1efb1095-17d0-4b98-9c16-406ffb9d3672	EXPO	Large-scale exhibitions showcasing products and innovations.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-d37da434-a9e8-4a41-b899-c9318ac75c8b	CARNIVAL	Festivals featuring parades, costumes, and celebrations.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-fa3e2d9c-32f0-4c5c-bfee-890561926589	GEEK_CONVENTION	Conventions for fans of geek culture, including comics and gaming.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-50725538-2e29-463d-a82c-98a4a79a8f03	FOOD_FESTIVAL	Festivals celebrating food and culinary traditions.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-c5a55f2f-afa8-45a2-8239-3ea2939c00fc	WINE_FESTIVAL	Festivals dedicated to wine tasting and appreciation.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-f1fe5617-5bb2-47ef-a41f-3d8522c37f7b	BOOK_FAIR	Events showcasing books and literature.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-3dbc1193-b4d4-423e-b9ff-24ca7c9f23f9	COMIC_CON	Conventions for fans of comics, movies, and pop culture.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-ffe04446-9061-4de4-b85d-4688194a78c2	HARVEST_FESTIVAL	Festivals celebrating the harvest season.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-b8b7464c-01d3-464b-8e40-f782eae3d488	FLOWER_FESTIVAL	Festivals showcasing flowers and floral arrangements.	$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7
$EvC-250ea1b3-6402-4113-9db8-ba4a77c900b9	NIGHTCLUB_PARTY	Parties held in nightclubs with music and dancing.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-c073fcfd-8cda-4555-815d-629a47d81eef	POOL_PARTY	Outdoor parties held around a pool.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-c5a6f994-cc88-4e4e-9d18-d7d382a2f670	COSTUME_PARTY	Parties where attendees wear costumes.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-7eb9e80a-6bbc-4f4b-973d-7b81b33a2d76	BEACH_PARTY	Parties held on the beach, often with music and bonfires.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-637aab7b-30a1-47d8-baa8-2d103d975c74	THEMED_PARTY	Parties centered around a specific theme.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-21580a0e-e740-4de4-8ae7-27d721e06dce	GLOW_PARTY	Parties featuring neon lights and glow-in-the-dark decorations.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-7dfdef34-361e-4374-b87e-28ee8fc67f69	SILENT_DISCO	Parties where attendees listen to music through headphones.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-11b2e70e-f1e5-4203-803a-87942419cf95	ROOFTOP_PARTY	Parties held on rooftops, often with city views.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-198069b5-1dff-4158-8a5a-7ed59ba75e59	NEW_YEAR_EVE_PARTY	Parties celebrating the arrival of the new year.	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$EvC-f98fa528-94b5-45f0-b7b0-eeba1771b2bb	ESPORT_TOURNAMENT	Competitive gaming tournaments featuring professional players.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-e7e2e53b-1477-4457-b963-af63f55f4c32	GAMING_CONFERENCE	Conferences focused on gaming and the gaming industry.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-ce4822d3-a719-45ec-96d4-af8a627e5315	LAN_PARTY	Gaming events where participants connect via a local network.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-2f41008b-2e54-423b-8a3e-40cf1c8e277a	VIDEO_GAME_RELEASE	Events celebrating the release of new video games.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-88031686-c1b6-40ce-b542-cbf8b3bf95f2	BOARD_GAME_NIGHT	Events where participants play board games.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-271a70ca-7fd6-4c30-8655-3e8df8944fa2	COSPLAY_EVENT	Events where participants dress up as characters from games or movies.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-35a919ed-c367-4e25-994e-c0d63fe5159a	GAMING_MARATHON	Events where participants play games for an extended period.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-cf816261-9155-41a1-9c45-adb5c17d1249	VIRTUAL_REALITY_EXPO	Expos showcasing virtual reality technology and games.	$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352
$EvC-988fe3f1-a96c-435f-b6d2-282e30a17af1	PROFESSIONAL_FAIR	Fairs focused on career opportunities and professional development.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-6b67ca22-d853-48eb-a2b8-d14902f2a7da	BUSINESS_CONFERENCE	Conferences focused on business strategies and networking.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-25e8a149-bc03-4e3d-ae8b-cad60dc63268	STARTUP_PITCH_EVENT	Events where startups pitch their ideas to investors.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-d8098df9-ce7a-45c0-94e9-d568bbc30e51	CORPORATE_SOCIAL	Social events organized by companies for employees and clients.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-76e71857-ef0c-487a-9415-afc5f653f366	NETWORKING_EVENT	Events designed for professionals to connect and network.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-b0992384-f2d6-47ef-a844-6cd33909f143	INVESTOR_PITCH	Events where entrepreneurs pitch their ideas to potential investors.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-be7b53ba-5cc1-48b0-a0b2-1e439b4f41e0	TRADE_SHOW	Exhibitions showcasing products and services for a specific industry.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-185770c4-280e-4146-b8d7-72dc874bdb98	CORPORATE_TRAINING	Training sessions for employees to develop professional skills.	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$EvC-4f346135-aba3-4cd4-885a-ab1f92c3c3d2	RELIGIOUS_CEREMONY	Ceremonies and rituals associated with religious practices.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-3cac4e56-c675-4e03-8867-83772727f9bd	SPIRITUAL_SONGS_AND_MUSIC	Events featuring spiritual or religious music.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-4450a44d-45d5-4d92-af04-fb3f1618860c	PILGRIMAGE	Journeys to sacred sites for religious purposes.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-fb72ffc7-0e14-40c4-acb7-d0c6b836b89a	FAITH_HEALING	Events focused on spiritual or faith-based healing practices.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-ecb4a02f-f245-4c44-a6ba-f3e98cd51d08	SPIRITUAL_RETREAT	Retreats focused on spiritual growth and reflection.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-22e38d4f-24d3-4c3b-99ea-94f2279814a6	PRAYER_MEETING	Gatherings for communal prayer and worship.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-30e22619-cc2f-498c-8a0b-5c6c0f2c0a67	RELIGIOUS_CONFERENCE	Conferences focused on religious teachings and discussions.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-3bc25e20-800a-4b6d-b037-c88f69442abc	HOLY_DAY_CELEBRATION	Celebrations of religious holidays and holy days.	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$EvC-a1c6d475-ab8b-4dcc-9b71-214cb35e68eb	WINE_TASTING	Events where participants sample and learn about different wines.	$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98
$EvC-b2638221-a787-49e9-a45a-4d8fedda6ed3	BEER_FESTIVAL	Festivals celebrating beer and brewing traditions.	$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98
$EvC-a2e5be02-9788-4590-9b15-ecc2605ef6d5	COFFEE_EXPO	Expos showcasing coffee and coffee-making techniques.	$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98
$EvC-9e587782-e2c6-4b29-a36b-a5d2fdb24c4f	COCKTAIL_WORKSHOP	Workshops focused on making and enjoying cocktails.	$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98
$EvC-2e7447ee-8742-4dcd-8bb9-391620b97f30	CHOCOLATE_FESTIVAL	Festivals celebrating chocolate and chocolate-based treats.	$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98
$EvC-11f095bb-261a-400a-bd45-b8966a77c238	STREET_FOOD_FESTIVAL	Festivals featuring a variety of street food vendors.	$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98
$EvC-84326d18-96a1-47a6-adf8-c93b6e7776e4	CHARITY_GALA	Formal events raising funds for charitable causes.	$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc
$EvC-57c2a949-e835-4482-a9cd-c63eb5a159a2	CHARITY_RUN	Running events organized to raise money for charity.	$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc
$EvC-00404221-8553-4658-b4d9-c9d8fb69fa6c	VOLUNTEER_EVENT	Events where participants volunteer for a cause.	$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc
$EvC-b815b989-f81b-4f9f-929f-f4b6a670cc0b	FUNDRAISING_GALA	Gala events organized to raise funds for a specific cause.	$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc
$EvC-03a7c3c5-8ac9-457b-8e5e-728f51537e2b	CHARITY_AUCTION	Auctions where proceeds go to charitable organizations.	$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc
$EvC-39a721e6-1c60-4ddd-be87-e698b76f821e	COMMUNITY_SERVICE	Events focused on community improvement and service.	$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc
$EvC-0ea96a17-3721-4c46-803e-39be83fff78d	TRAVEL_EXPO	Expos showcasing travel destinations and services.	$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5
$EvC-56366da6-f19e-4ec4-9f79-3093d2cb2086	ADVENTURE_TOUR	Guided tours focused on adventure and exploration.	$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5
$EvC-17929228-ab17-485f-af1d-11630831e2c5	EXOTIC_TRAVEL	Events promoting travel to exotic and unique destinations.	$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5
$EvC-af8d7b64-31d9-4b79-b90c-b0949da56b96	CRUISE_EVENT	Events promoting cruise vacations and experiences.	$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5
$EvC-f26ffc46-5049-4236-b965-9a5aa665fe5c	ADVENTURE_CAMP	Camps focused on outdoor and adventure activities.	$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5
$EvC-595dbbec-5395-4f84-887f-152c51227833	CULTURAL_IMMERSION	Events focused on experiencing and learning about different cultures.	$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5
$EvC-6eb67575-2d05-4b8b-85c1-a956f32eb7fe	FASHION_SHOW	Events showcasing the latest fashion trends and designs.	$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28
$EvC-10f2bec4-2460-44fa-9648-591bc437a583	FASHION_EXPO	Expos featuring fashion brands and designers.	$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28
$EvC-b23910d2-e3bf-4acc-8d78-0d0635627cb2	FASHION_WORKSHOP	Workshops focused on fashion design and styling.	$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28
$EvC-328abd99-7e91-43d8-ac68-2b205f77f6db	FASHION_AWARD	Award ceremonies celebrating achievements in fashion.	$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28
$EvC-d53d20c4-1643-4b8b-adfb-a40d2980f429	POP_UP_STORE	Temporary retail stores showcasing fashion products.	$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28
$EvC-fad84248-398c-43a0-a1c8-7e0341297cfd	DESIGNER_SHOWCASE	Events showcasing the work of fashion designers.	$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28
$EvC-147f06a2-56fe-40e5-9703-fc3cb35f138b	TECH_CONFERENCE	Conferences focused on technology and innovation.	$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99
$EvC-d100ef71-8f14-446f-93e8-73c63d5eb010	GADGET_EXPO	Expos showcasing the latest gadgets and tech products.	$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99
$EvC-d6d0af6a-fb1f-40d0-9414-0dc5731b72a6	INNOVATION_SUMMIT	Summits focused on technological innovation and trends.	$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99
$EvC-55e33cfc-6ca7-460f-b877-b462f4d53689	AI_CONFERENCE	Conferences focused on artificial intelligence and machine learning.	$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99
$EvC-d53502d2-7593-4384-871f-f020136cc43f	ROBOTICS_EXPO	Expos showcasing robotics and automation technologies.	$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99
$EvC-503fcf85-5eba-46c9-bf3f-ac4ff469a634	CYBERSECURITY_SUMMIT	Summits focused on cybersecurity and data protection.	$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99
$EvC-6f4c435a-9831-4621-a831-d19bce5d0dd6	CLEANUP_EVENT	Events focused on cleaning up public spaces and the environment.	$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789
$EvC-644caa8d-ce4e-4164-948a-dc121cc90468	GREEN_CONFERENCE	Conferences focused on environmental sustainability.	$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789
$EvC-567caba4-429e-4d35-b3b4-c2d3279750d9	ECO_FESTIVAL	Festivals celebrating eco-friendly practices and products.	$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789
$EvC-20d17c9e-a009-44f5-85f8-7c3bc440e757	TREE_PLANTING	Events focused on planting trees and improving green spaces.	$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789
$EvC-f37cd4ba-17f4-469e-87f4-ca54292c7dc7	SUSTAINABILITY_WORKSHOP	Workshops focused on sustainable living and practices.	$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789
$EvC-0edbc088-3c3f-457b-abc4-dc9e1ab1ede1	ECO_FRIENDLY_MARKET	Markets featuring eco-friendly and sustainable products.	$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789
$EvC-22aeafe6-fbc7-4caf-ba83-980ae85008cf	KIDS_THEATER	Theater performances designed for children.	$EvT-37b55e0d-9aeb-4246-9733-741325368808
$EvC-10a08a3d-aa2a-484b-b5a7-d8101ce86fb4	FAMILY_CARNIVAL	Carnivals designed for families and children.	$EvT-37b55e0d-9aeb-4246-9733-741325368808
$EvC-7743c33d-a5c2-4d0f-ba44-4356ccbf1d1b	CHILDREN_WORKSHOP	Workshops designed for children to learn and create.	$EvT-37b55e0d-9aeb-4246-9733-741325368808
$EvC-2bcdbd90-649a-4d10-bda7-f8e0782a9cc4	AMUSEMENT_PARK_EVENT	Events held at amusement parks for families.	$EvT-37b55e0d-9aeb-4246-9733-741325368808
$EvC-d4f0e569-96a8-4ee9-adc1-f0d024bd05d7	STORYTELLING_SESSION	Sessions where stories are told to children.	$EvT-37b55e0d-9aeb-4246-9733-741325368808
$EvC-408f59f9-f76e-4079-8a25-e043022ed90f	YOGA_CLASS	Classes focused on yoga and mindfulness.	$EvT-7038ebe5-fd99-4fac-906c-4b31b11e7663
$EvC-ea432155-e029-4f14-9b42-c4db58d4a83c	MEDITATION_SESSION	Sessions focused on meditation and relaxation.	$EvT-7038ebe5-fd99-4fac-906c-4b31b11e7663
$EvC-a8acd5e3-d312-42c2-bf04-a1e0bd7b49da	HEALTH_FITNESS_CHALLENGE	Events focused on physical fitness and challenges.	$EvT-7038ebe5-fd99-4fac-906c-4b31b11e7663
$EvC-e0d74c48-fefe-4607-88f0-cb21efa55975	WELLNESS_RETREAT	Retreats focused on health and wellness.	$EvT-7038ebe5-fd99-4fac-906c-4b31b11e7663
$EvC-f2e416ba-d072-43ec-aa7e-71b55544ac9d	HEALTH_FAIR	Fairs focused on health education and services.	$EvT-7038ebe5-fd99-4fac-906c-4b31b11e7663
$EvC-db7e4fe3-bbd0-44fa-a60c-04f63b79b721	BOOK_SIGNING	Events where authors sign copies of their books.	$EvT-f11273f7-18bc-47c6-93f5-f7d9787f56cc
$EvC-03cc8dc7-1108-4934-ae09-c301485879ef	AUTHOR_TALK	Talks and discussions with authors about their work.	$EvT-f11273f7-18bc-47c6-93f5-f7d9787f56cc
$EvC-d8ea49dc-58e2-406c-b780-e1c18d22b88c	POETRY_READING	Events where poets read their work aloud.	$EvT-f11273f7-18bc-47c6-93f5-f7d9787f56cc
$EvC-c3b2fa48-7b40-4a5b-8f69-acf23eec07aa	WRITING_WORKSHOP	Workshops focused on writing and storytelling.	$EvT-f11273f7-18bc-47c6-93f5-f7d9787f56cc
$EvC-2e34fdd0-3010-497d-abdf-eb4a1de2cadb	BOOK_CLUB_MEETING	Meetings where book lovers discuss a selected book.	$EvT-f11273f7-18bc-47c6-93f5-f7d9787f56cc
$EvC-2e9891aa-6464-41a3-8509-194b0ba1f676	STAND_UP_COMEDY	Live performances by comedians delivering humorous monologues.	$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8
$EvC-8614c5c0-9f1b-4c96-a1e6-44191cdbf94b	IMPROV_COMEDY	Comedy performances where actors create scenes on the spot.	$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8
$EvC-2d1157d7-173d-4a3d-a17c-9d9325bea6a6	COMEDY_CLUB_NIGHT	Nights dedicated to comedy performances at a club.	$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8
$EvC-3597aa18-56e2-4ae5-8583-53241c6594d5	SATIRE_SHOW	Shows featuring satirical humor and commentary.	$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8
$EvC-48dc22f5-95bb-43ea-8d3b-478f86da5624	COMEDY_FESTIVAL	Festivals dedicated to comedy performances.	$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8
$EvC-e2016c1b-571d-45d4-aeec-3b8d47f227fc	NIGHTCLUB_EVENT	Events held at nightclubs featuring music and dancing.	$EvT-4380b7eb-5257-4586-96ce-0300665d0f84
$EvC-969610e4-1521-4233-8484-3e03b9653b2f	LOUNGE_PARTY	Parties held in lounges with a relaxed atmosphere.	$EvT-4380b7eb-5257-4586-96ce-0300665d0f84
$EvC-09abbff5-5f40-44e3-8df5-8f1d40552659	NIGHTLIFE_KARAOKE_NIGHT	Nights where participants sing along to recorded music.	$EvT-4380b7eb-5257-4586-96ce-0300665d0f84
$EvC-bfcab5c5-f86f-4497-b657-87a9c19db75c	THEMED_NIGHT	Nights with a specific theme for music and decorations.	$EvT-4380b7eb-5257-4586-96ce-0300665d0f84
$EvC-1d7e5a85-129c-4480-ba9e-d6d660701383	AFTER_PARTY	Parties held after a main event, often late into the night.	$EvT-4380b7eb-5257-4586-96ce-0300665d0f84
$EvC-8747ff3d-ffc3-43e8-800f-85a095da9d7f	HIKING_TOUR	Guided tours focused on hiking and exploring nature.	$EvT-c1854e57-6aa0-40ba-b204-ea36ad4d8cd0
$EvC-3f39761d-beed-490d-869a-5ea2c2f9c0c5	CAMPING_EVENT	Events focused on camping and outdoor activities.	$EvT-c1854e57-6aa0-40ba-b204-ea36ad4d8cd0
$EvC-0e4452de-6e5e-45c5-aae3-c0ad581e103a	ROCK_CLIMBING	Events focused on rock climbing and bouldering.	$EvT-c1854e57-6aa0-40ba-b204-ea36ad4d8cd0
$EvC-98a878f9-6695-4e27-8df3-dbcf72131526	KAYAKING_TOUR	Guided tours focused on kayaking and water exploration.	$EvT-c1854e57-6aa0-40ba-b204-ea36ad4d8cd0
$EvC-3353920f-4fdd-45bf-b9e3-1b1765e3d880	ADVENTURE_RACE	Races combining multiple outdoor activities.	$EvT-c1854e57-6aa0-40ba-b204-ea36ad4d8cd0
$EvC-034e8353-27bf-49ba-ac58-898041344cf6	NEIGHBORHOOD_FAIR	Fairs celebrating local neighborhoods and communities.	$EvT-37c8dc30-9e2b-40b9-96fb-ec77bd3bd2ac
$EvC-8fb92144-9886-404a-9859-d376026c80fe	COMMUNITY_POTLUCK	Gatherings where community members share food and stories.	$EvT-37c8dc30-9e2b-40b9-96fb-ec77bd3bd2ac
$EvC-9ecf2534-1153-41e1-8a00-1bc89f03d249	VOLUNTEER_MEETUP	Meetups focused on volunteering and community service.	$EvT-37c8dc30-9e2b-40b9-96fb-ec77bd3bd2ac
$EvC-da61062e-d14b-4e59-978a-071aec3a5ca1	SOCIAL_CLUB_EVENT	Events organized by social clubs for members.	$EvT-37c8dc30-9e2b-40b9-96fb-ec77bd3bd2ac
$EvC-718d3e4a-f87b-4c75-9943-887ca8ef5430	CULTURAL_EXCHANGE	Events focused on cultural exchange and understanding.	$EvT-37c8dc30-9e2b-40b9-96fb-ec77bd3bd2ac
$EvC-850610c8-170f-4f1a-9eb6-d8bf269a188b	HISTORICAL_TOUR	Guided tours focused on historical landmarks and sites.	$EvT-9dd018d5-dfe9-4ea6-89f8-27017fdcea9e
$EvC-e15cef6d-e8ca-4d70-bb6a-cde895cfcf22	REENACTMENT_EVENT	Events where historical events are reenacted.	$EvT-9dd018d5-dfe9-4ea6-89f8-27017fdcea9e
$EvC-9e6cc303-79bd-4b83-8d68-09c5bf77b54f	HERITAGE_FESTIVAL	Festivals celebrating cultural heritage and traditions.	$EvT-9dd018d5-dfe9-4ea6-89f8-27017fdcea9e
$EvC-09913eae-a595-4138-a972-415631838456	ARCHAEOLOGY_TALK	Talks and discussions focused on archaeology and history.	$EvT-9dd018d5-dfe9-4ea6-89f8-27017fdcea9e
$EvC-b1ba1f7c-175c-4e65-ad79-5e3726499231	MUSEUM_NIGHT	Special events held at museums, often with extended hours.	$EvT-9dd018d5-dfe9-4ea6-89f8-27017fdcea9e
$EvC-a4633e70-ac74-4615-8faa-6a0aaf0e3aa4	SCIENCE_FAIR	Fairs showcasing scientific projects and experiments.	$EvT-972c14e2-0af8-4b22-9b2b-367ff86849c5
$EvC-0b94fed5-ad4c-414a-aa53-c643d0697edb	TECH_DEMO	Demonstrations of new technology and innovations.	$EvT-972c14e2-0af8-4b22-9b2b-367ff86849c5
$EvC-b8126694-7104-4842-b83b-446900d2cb93	STEM_WORKSHOP	Workshops focused on science, technology, engineering, and math.	$EvT-972c14e2-0af8-4b22-9b2b-367ff86849c5
$EvC-2e9d4b99-8a14-4fed-9de4-73dfddef5e67	ASTRONOMY_NIGHT	Events focused on stargazing and astronomy.	$EvT-972c14e2-0af8-4b22-9b2b-367ff86849c5
$EvC-b6317d50-a087-420e-880f-3da1a15baccc	INNOVATION_EXPO	Expos showcasing innovative technologies and ideas.	$EvT-972c14e2-0af8-4b22-9b2b-367ff86849c5
$EvC-6c84161f-95c4-4c71-8115-02914ff8bd43	WILDLIFE_TOUR	Guided tours focused on observing wildlife in their natural habitat.	$EvT-4f8f30be-a7b0-4269-b3ae-0d4f23e7014c
$EvC-60e5d37b-1814-4132-8897-8299500eb828	PET_ADOPTION_EVENT	Events where pets are available for adoption.	$EvT-4f8f30be-a7b0-4269-b3ae-0d4f23e7014c
$EvC-0d2d0a25-19d8-4e42-846f-6a461b5f9725	BIRD_WATCHING	Events focused on observing and identifying birds.	$EvT-4f8f30be-a7b0-4269-b3ae-0d4f23e7014c
$EvC-137d7df5-70c0-4226-9c06-1146721e7e5d	NATURE_PHOTOGRAPHY	Events focused on capturing nature through photography.	$EvT-4f8f30be-a7b0-4269-b3ae-0d4f23e7014c
$EvC-adc9e2ca-8f43-4abb-9d0b-c6e346b86206	ANIMAL_SANCTUARY_VISIT	Visits to animal sanctuaries to learn about and interact with animals.	$EvT-4f8f30be-a7b0-4269-b3ae-0d4f23e7014c
\.


--
-- Data for Name: events_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.events_type (id, event_type, description) FROM stdin;
$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80	ENTERTAINMENT	Events focused on entertainment, including performances, shows, and fun activities for all ages.
$EvT-11db6826-36b3-4e26-944d-ea66cf088777	MUSIC	Events centered around music, such as concerts, festivals, and live performances.
$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4	SPORTS	Events related to sports, including matches, tournaments, and fitness challenges.
$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee	THEATER_AND_SHOWS	Events featuring theatrical performances, plays, and live shows.
$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd	EDUCATION	Events focused on learning, including workshops, seminars, and educational courses.
$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc	CINEMA	Events related to movies, including screenings, premieres, and film festivals.
$EvT-b9c523ef-6225-4bbe-baa3-8bd1cc724e80	EXHIBITIONS_AND_CULTURE	Events showcasing art, culture, and exhibitions.
$EvT-f0b91078-26d7-4c32-9eb5-56aeac65bbb7	FESTIVALS_AND_FAIRS	Events celebrating festivals, fairs, and cultural gatherings.
$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8	PARTIES	Events focused on parties, celebrations, and social gatherings.
$EvT-4448be5d-3444-4ddc-8085-4d5e82f8f352	GAMING	Events related to gaming, including esports, tournaments, and gaming conventions.
$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9	BUSINESS	Events focused on business, networking, and professional development.
$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31	RELIGIOUS	Events related to religious and spiritual activities.
$EvT-30e6401a-741c-4c75-99a4-cdaeaa82bc98	FOOD_AND_DRINK	Events centered around food, drinks, and culinary experiences.
$EvT-0df3fd96-69a0-4c68-b1dc-568223e5eacc	CHARITY	Events focused on charity, fundraising, and community service.
$EvT-c43cad6d-9e94-4540-b27c-871ff2f79ed5	TRAVEL	Events related to travel, adventure, and exploration.
$EvT-98bc26bb-e2e6-4537-a84c-0b191ed61b28	FASHION	Events focused on fashion, design, and style.
$EvT-036b7811-4a09-4a5f-b8f3-5aa78c2eef99	TECHNOLOGY	Events centered around technology, innovation, and gadgets.
$EvT-9e872c5e-4ec0-42be-af9f-79ece9c7b789	ENVIRONMENTAL	Events focused on environmental awareness and sustainability.
$EvT-37b55e0d-9aeb-4246-9733-741325368808	FAMILY_AND_KIDS	Events designed for families and children.
$EvT-7038ebe5-fd99-4fac-906c-4b31b11e7663	HEALTH_AND_WELLNESS	Events focused on health, wellness, and fitness.
$EvT-f11273f7-18bc-47c6-93f5-f7d9787f56cc	LITERATURE_AND_BOOKS	Events centered around literature, books, and writing.
$EvT-002c6844-0cbd-469a-be33-b2a95de02ba8	COMEDY	Events focused on comedy and humor.
$EvT-4380b7eb-5257-4586-96ce-0300665d0f84	NIGHTLIFE	Events related to nightlife, clubs, and evening entertainment.
$EvT-c1854e57-6aa0-40ba-b204-ea36ad4d8cd0	OUTDOOR_AND_ADVENTURE	Events focused on outdoor activities and adventure.
$EvT-37c8dc30-9e2b-40b9-96fb-ec77bd3bd2ac	COMMUNITY_AND_SOCIAL	Events focused on community building and social interaction.
$EvT-9dd018d5-dfe9-4ea6-89f8-27017fdcea9e	HISTORY_AND_HERITAGE	Events centered around history, heritage, and cultural preservation.
$EvT-972c14e2-0af8-4b22-9b2b-367ff86849c5	SCIENCE_AND_TECH	Events focused on science, technology, and innovation.
$EvT-4f8f30be-a7b0-4269-b3ae-0d4f23e7014c	ANIMALS_AND_NATURE	Events related to animals, nature, and wildlife.
\.


--
-- Data for Name: has_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.has_type (id_event, id_events_type) FROM stdin;
$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$Evt-5be48f4c-790a-4206-835f-ba39210c54f7	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$Evt-d0de138a-6c43-45c5-86f9-4772b8f80acc	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-300effb5-2665-4a6f-b285-46242f51d545	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$Evt-300effb5-2665-4a6f-b285-46242f51d545	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	$EvT-e04d4c53-6dbb-45d6-85c5-23d739659b80
$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	$EvT-4aa6ed58-5733-4e15-8734-a1a3fdd313dc
$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$Evt-62eca069-e832-49b5-a37c-889ef52b2b30	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81	$EvT-85028c75-dfb6-48d8-bd85-c03fe6d713b8
$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3	$EvT-d4dedf25-ab69-4c5f-bd7f-2c4e6b74ada9
$Evt-c3caa18d-2087-43da-b933-7a34b915287b	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$Evt-d62731db-1fae-484e-9780-4b7e804179f4	$EvT-c8ada1cb-75b8-4788-a13b-ef702e8d4c31
$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$Evt-4f4c6994-cc24-40fe-85ed-6fc738d43999	$EvT-4df24a0f-dc72-4e8a-9f91-18499d9690dd
$Evt-363a87d1-46ac-4002-8282-d80eca467558	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea	$EvT-d86d97fb-e94d-4710-bfc3-aa7c0e0c26d4
$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f	$EvT-29cf7d4f-9a65-4f5f-8b23-dd582ef7deee
$Evt-feabf759-7c25-41ee-a724-81b3df34b760	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-abf8cfd1-9fd4-40b8-9544-99527995334c	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-63e96074-ee58-4688-af06-f914ba7d6a55	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-47a7235a-faec-492a-94cb-3300238ce56e	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a	$EvT-11db6826-36b3-4e26-944d-ea66cf088777
\.


--
-- Data for Name: payment_mode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment_mode (id, description, payment_api_url, provider, type, created_at, updated_at, logo_img_path, status) FROM stdin;
$PmD-afb64532-3a83-4ed3-bb70-308d757da4ed	Mobile money payment via Mvola Madagascar.	https://api.mvola.mg/payment	MVOLA_MADAGASCAR	MOBILE_MONEY	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-99c4b3fe-d3ae-47f9-8cfe-7e9eca051ec1	Mobile money payment via Orange Money.	https://api.orange.money/payment	ORANGE_MONEY	MOBILE_MONEY	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-7748a4f2-d1da-41f1-8478-e8f218fffc37	Mobile money payment via Airtel Money.	https://api.airtel.money/payment	AIRTEL_MONEY	MOBILE_MONEY	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-07c04472-c4ad-494a-a3b1-91bacb28b182	Payment via Visa credit or debit card.	https://api.visa.com/payment	VISA	VISA	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-7d6dd7b7-6772-4928-b061-bb27f12b883a	Payment via Mastercard credit or debit card.	https://api.mastercard.com/payment	MASTERCARD	MASTERCARD	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-b20ffd65-ae16-455e-8448-e31ba19536c1	Payment via PayPal for online transactions.	https://api.paypal.com/payment	PAYPAL	PAYPAL	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-dc20697e-a310-4c0a-9ef6-f2e686bc66d7	Payment via Stripe for online transactions.	https://api.stripe.com/payment	STRIPE	VISA	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-8ef91515-5e79-47b9-8f35-d15dd2bc6b45	Payment via Stripe for online transactions.	https://api.stripe.com/payment	STRIPE	MASTERCARD	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-74827ee6-25f7-4a25-89c7-818f41222c39	Payment via Google Pay for online and in-store transactions.	https://api.googlepay.com/payment	GOOGLE_PAY	VISA	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-c43622f9-140c-4172-a0e5-05cae5149350	Payment via Apple Pay for online and in-store transactions.	https://api.applepay.com/payment	APPLE_PAY	VISA	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-499a8b6b-8b31-461b-a38f-fcea5d7deb8c	Payment via Bitcoin for cryptocurrency transactions.	https://api.bitcoin.com/payment	BITCOIN	CRYPTOCURRENCY	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
$PmD-1034e7b8-405f-4177-ba81-36a4c9543d76	Payment via cash for in-person transactions.	N/A	CASH	CASH	2025-03-02 22:36:57.763942	2025-03-02 22:36:57.763942	/payment-mode/no-img-yet/	f
\.


--
-- Data for Name: refresh_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.refresh_token (refresh_token, created_at, expires_at, is_valid, user_email) FROM stdin;
$Trf-afe4daa9-33be-463f-9389-60ea3525b4cd	2025-03-19 17:53:56.261361	2025-03-21 17:53:56.261361	f	tapakila.noreply@gmail.com
$Trf-a5f6d937-903e-4e48-8deb-f44857fd046b	2025-03-20 13:45:19.171883	2025-03-22 13:45:19.171883	f	tapakila.noreply@gmail.com
$Trf-da18be05-1d0c-4aef-b47a-287b5c4a0f81	2025-03-20 14:10:10.827432	2025-03-22 14:10:10.827432	t	tapakila.noreply@gmail.com
$Trf-15acff18-e1d9-4ad1-b549-d9fe232c8b8a	2025-03-20 14:28:12.277671	2025-03-22 14:28:12.277671	t	tapakila.noreply@gmail.com
$Trf-71d62ee8-48b7-4a21-994c-ea638de99764	2025-03-20 15:10:35.003704	2025-03-24 15:10:35.003704	t	a.razafindratelo@gmail.com
$Trf-00ecd437-0ab5-4ccb-a3e8-c9e76e75854f	2025-03-22 12:15:54.685537	2025-03-26 12:15:54.685537	t	a.razafindratelo@gmail.com
$Trf-598dbfb2-3984-4151-9643-05614da82482	2025-03-25 11:55:18.829811	2025-03-29 11:55:18.829811	t	tapakila.noreply@gmail.com
$Trf-9ec005f1-c7b7-41f4-8c61-b316ec7cdb5d	2025-04-02 16:30:04.596099	2025-04-06 16:30:04.596099	t	hei.tsanta.3@gmail.com
\.


--
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ticket (id, ticket_number, status, purchased_at, qr_code_path, payement_ref, ticket_owner_name, user_email, id_ticket_price, id_payment_mode) FROM stdin;
1	1	t	2025-03-30 13:28:27.239937	...	ref	me	tapakila.noreply@gmail.com	$TkP-c0ad1c27-3981-44b7-b244-d3b20e71ef14	$PmD-1034e7b8-405f-4177-ba81-36a4c9543d76
$Tkt-ad728192-c90e-4c22-9f7c-ad4c13b7cdd3	1	t	2025-04-01 19:55:25.924841	src/main/resources/static/assets/image/user/tapakila.noreply@gmail.com/qr_code/$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd/1743526525711.png	3a170010-b4d0-4bc9-aafc-48262cfc54f1	Hou lou lou lou	tapakila.noreply@gmail.com	$TkP-109436f1-bf0b-4aea-905b-b1a20d74dbb2	$PmD-1034e7b8-405f-4177-ba81-36a4c9543d76
$Tkt-79a6df14-682b-4c37-b847-4905ee47fd8c	2	t	2025-04-01 19:55:26.299558	src/main/resources/static/assets/image/user/tapakila.noreply@gmail.com/qr_code/$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd/1743526526096.png	3d8f5347-8cba-470d-8155-fac4bc0cd4d1	Pou lou lou lou	tapakila.noreply@gmail.com	$TkP-109436f1-bf0b-4aea-905b-b1a20d74dbb2	$PmD-1034e7b8-405f-4177-ba81-36a4c9543d76
\.


--
-- Data for Name: ticket_price; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ticket_price (id, price, created_at, max_number, id_ticket_type, id_event, currency) FROM stdin;
$TkP-2057c8db-b3ba-4fbb-8aad-e0eb36f58e01	15000	2025-01-27 00:00:00	100	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-62eca069-e832-49b5-a37c-889ef52b2b30	MGA
$TkP-ec9821bc-0ece-4166-afb3-e0cce4a7a568	25000	2025-01-27 00:00:00	150	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81	MGA
$TkP-bfc313b5-107a-451a-b2b6-516826b592de	30000	2025-01-27 00:00:00	100	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81	MGA
$TkP-f4880e30-353c-470f-90a8-58ddd99cbdf1	20000	2025-01-27 00:00:00	150	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-5209737c-ca91-4c47-b34a-9f7b1c58cc81	MGA
$TkP-9ac5cbf3-6423-402d-9664-54364fa51381	25000	2025-03-27 00:00:00	100	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3	MGA
$TkP-3116ff93-3f10-4181-badc-c82ee00e2b67	30000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3	MGA
$TkP-23e16c3b-d8e0-4dff-9a4f-5947aa696d65	20000	2025-03-27 00:00:00	150	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-22e44de8-aaf3-45a2-a284-064a3e0c5ca3	MGA
$TkP-02410891-1f4c-4aa0-8a45-95073a02f870	15000	2025-03-27 00:00:00	100	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-c3caa18d-2087-43da-b933-7a34b915287b	MGA
$TkP-76fbd708-dc2d-4161-b841-89e79e08783f	20000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-c3caa18d-2087-43da-b933-7a34b915287b	MGA
$TkP-38d1e587-efcc-4e12-99f2-cc959b8a008c	10000	2025-03-27 00:00:00	150	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-c3caa18d-2087-43da-b933-7a34b915287b	MGA
$TkP-7055cfa9-43fe-4631-b644-65723659b9fa	20000	2025-03-27 00:00:00	100	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-d62731db-1fae-484e-9780-4b7e804179f4	MGA
$TkP-71515294-9b21-4a9f-b511-999299c4c61d	25000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-d62731db-1fae-484e-9780-4b7e804179f4	MGA
$TkP-26029716-2bc6-4e60-b23b-95766e5ffe3f	15000	2025-03-27 00:00:00	150	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-d62731db-1fae-484e-9780-4b7e804179f4	MGA
$TkP-ed684875-d5c4-4497-a645-89a7e4938a09	20000	2025-03-27 00:00:00	50	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c	MGA
$TkP-6c008b3c-c348-48b9-8cb8-8f945babe5eb	25000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c	MGA
$TkP-76532622-21be-4f11-9adf-017d053aa40e	15000	2025-03-27 00:00:00	100	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-46617dfd-9b11-47c0-afaf-ca9338268d1c	MGA
$TkP-ec3f72fe-ec20-444a-8108-d56dd37700af	10000	2025-03-27 00:00:00	50	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705	MGA
$TkP-f0bcd5ea-645c-4a0c-8e39-29c7d87822c8	15000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705	MGA
$TkP-8ebe1a34-56d8-4530-9fc5-e03019ede407	5000	2025-03-27 00:00:00	100	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-4f45cd17-91d2-4c4b-987d-688d3d62f705	MGA
$TkP-dd5de3da-bb60-4ab8-9be0-99b10bdba7c7	10000	2025-03-27 00:00:00	150	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff	MGA
$TkP-d84511ea-f49e-49e2-9990-1432d9db1927	15000	2025-03-27 00:00:00	100	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff	MGA
$TkP-441c43c5-fd42-471e-969f-640b88562ab7	5000	2025-03-27 00:00:00	150	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-6a557a83-3588-4213-bd1a-6f66c04eb5ff	MGA
$TkP-e87b3a30-eba9-41de-9a0e-b29e5ef85f32	15000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-363a87d1-46ac-4002-8282-d80eca467558	MGA
$TkP-153cede8-57e4-49a9-a158-029e2e454e7b	5000	2025-03-27 00:00:00	400	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-363a87d1-46ac-4002-8282-d80eca467558	MGA
$TkP-4483c08b-2567-4620-9043-097a997f9813	10000	2025-03-27 00:00:00	400	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad	MGA
$TkP-e3b1202f-e209-493c-9132-5fc58a5bfbae	5000	2025-03-27 00:00:00	400	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad	MGA
$TkP-c2899829-7613-4f3b-8fc9-2886881c9e3c	15000	2025-03-27 00:00:00	600	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9	MGA
$TkP-40c8fc70-2ac8-4d23-ab48-1f78d5e92a81	20000	2025-03-27 00:00:00	20	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f	MGA
$TkP-14ebb0f7-032d-4602-a469-d060eae92aaf	30000	2025-02-01 00:00:00	6000	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844	MGA
$TkP-dad96f06-a3b9-47ac-9399-dbbc33d9c60b	40000	2025-02-01 00:00:00	2000	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844	MGA
$TkP-3a7ff12c-4b94-4311-a174-fce3e9ff19a7	25000	2025-03-27 00:00:00	20	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f	MGA
$TkP-615a3988-ccba-47d9-be51-cab2104a5cf6	15000	2025-03-27 00:00:00	60	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-e4fa7320-79d9-46a0-9c10-b858f941590f	MGA
$TkP-3a33bb3c-a0d7-45ad-abe1-76806acdafbd	25000	2025-03-27 00:00:00	100	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-feabf759-7c25-41ee-a724-81b3df34b760	MGA
$TkP-14c5dd33-1f41-443d-9437-e3b91c2841b7	30000	2025-03-27 00:00:00	100	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-feabf759-7c25-41ee-a724-81b3df34b760	MGA
$TkP-23ec7cfd-21b5-45b5-a629-ac1669d77660	20000	2025-03-27 00:00:00	300	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-feabf759-7c25-41ee-a724-81b3df34b760	MGA
$TkP-f3bcaafb-70f5-4020-a27f-7a746a2d92dc	20000	2025-03-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-abf8cfd1-9fd4-40b8-9544-99527995334c	MGA
$TkP-6f3b5ce4-ee8e-4b6b-87f5-bb0a11964825	25000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-abf8cfd1-9fd4-40b8-9544-99527995334c	MGA
$TkP-914bbfb6-066b-470f-b052-7c8604c0d048	15000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-abf8cfd1-9fd4-40b8-9544-99527995334c	MGA
$TkP-82acb966-4ec3-45aa-be08-b2a73d620e3f	25000	2025-03-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-63e96074-ee58-4688-af06-f914ba7d6a55	MGA
$TkP-16b7de83-6d0c-49b2-a4fe-9f3302a4e84b	5000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-4f4c6994-cc24-40fe-85ed-6fc738d43999	MGA
$TkP-dedfb15e-82f6-4357-8761-e05b2e639e39	10000	2025-03-27 00:00:00	400	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-363a87d1-46ac-4002-8282-d80eca467558	MGA
$TkP-f21ee0bf-08c0-49ef-b5af-1f2836fe9f1d	25000	2025-03-05 13:04:22.754258	0	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	MGA
$TkP-a91be04d-e6b3-4a00-8585-749811a0a44c	3000	2025-03-05 13:26:01.774964	0	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2	MGA
$TkP-db504389-6c55-4c24-9a52-b2df9a95345b	20000	2025-02-01 00:00:00	2000	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-b3f1680e-e3f0-45a3-9f8c-2356b18e7844	MGA
$TkP-b1e3ac5d-f135-4d34-ac13-0c07c2d9273a	30000	2024-12-12 00:00:00	6000	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495	MGA
$TkP-da3666be-eb10-40a6-8ad5-0967b207d5ac	40000	2024-12-12 00:00:00	2000	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495	MGA
$TkP-24da40c7-e5fe-41e1-a6b3-c484daffb31b	20000	2024-12-12 00:00:00	2000	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-daa5e3a8-7302-4445-868c-d3a75c63b495	MGA
$TkP-e564a217-64b9-431e-9612-7e8b82d16222	25000	2025-03-27 00:00:00	600	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-300effb5-2665-4a6f-b285-46242f51d545	MGA
$TkP-8f148b48-1d34-47d7-842f-92c9a5e2e397	30000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-300effb5-2665-4a6f-b285-46242f51d545	MGA
$TkP-8ebf6bc2-3987-4ec1-9819-035e4392e838	20000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-300effb5-2665-4a6f-b285-46242f51d545	MGA
$TkP-c6814cb0-7c21-4162-8be0-fcf2d340ac8b	20000	2025-03-27 00:00:00	600	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	MGA
$TkP-1647ba33-6185-431e-ac8c-09eccd4bc64c	25000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	MGA
$TkP-02168044-31b5-4a9e-895c-1e94c75f813d	15000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-8d990a4e-699f-41ef-8aac-75c3bc961c29	MGA
$TkP-c33b5bc9-1d21-45e0-b096-cd775baabe1b	20000	2025-01-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a	MGA
$TkP-ed704044-4101-4c81-b82a-c4dd6da93463	25000	2025-01-27 00:00:00	100	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a	MGA
$TkP-34065706-cb5a-4591-a032-6952c0002ace	15000	2025-01-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-e208d7b9-d4e3-48b1-9bc9-fa255fc7f63a	MGA
$TkP-c6e103f3-3cc1-4b04-a0e5-8e185262c7df	5000	2025-03-05 13:26:01.774964	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2	MGA
$TkP-c0ad1c27-3981-44b7-b244-d3b20e71ef14	2500	2025-03-05 13:26:01.774964	22	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-689db3b3-a5b1-4a29-ad90-f462b591b2d2	MGA
$TkP-109436f1-bf0b-4aea-905b-b1a20d74dbb2	50000	2025-03-05 13:04:22.754258	0	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	MGA
$TkP-c8ac8411-fc1f-470a-9636-c9d3e04f45e2	30000	2025-03-05 13:04:22.754258	0	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Eve-7814f307-69f5-4f41-9ca5-55e8020083dd	MGA
$TkP-2d3d34b0-3316-4576-b6ff-79261e9b9387	15000	2025-03-05 13:04:22.754258	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-0f2144af-5d3a-4d37-ab50-62b6988cf6ad	MGA
$TkP-7738cbf0-6187-473c-b9a8-f614a4677d67	15000	2025-03-27 00:00:00	50	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea	MGA
$TkP-994d853f-6ee8-4129-959b-74e30cf69d56	20000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea	MGA
$TkP-3f56c1a9-4a08-4d97-817f-1995e9e6508f	10000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-5ee351a3-e693-4fe1-a75c-685c8dfe14ea	MGA
$TkP-87f29eb2-e6d5-4bd4-aabb-95b3f480e9d7	20000	2025-03-27 00:00:00	50	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff	MGA
$TkP-ae005b8c-7ad9-4a80-8895-a33245bcd18c	25000	2025-03-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff	MGA
$TkP-aa496ddb-91e2-4ee2-adbb-b20dec177693	15000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-94322a7c-2f32-43a1-beaf-eb8e4ca9cfff	MGA
$TkP-3c83a800-4545-4a94-9208-5545127fc6e4	20000	2025-03-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9	MGA
$TkP-f1b04699-468b-4cdc-bbd8-6033cc9952ae	25000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-8d74cc5d-b30f-4d6e-a99e-6c2b3c0445f9	MGA
$TkP-e1588900-1783-45c4-b9c9-13bf090ee063	20000	2025-01-27 00:00:00	100	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-62eca069-e832-49b5-a37c-889ef52b2b30	MGA
$TkP-0edc7326-318d-4ef3-8ac4-4fd0c41e949f	25000	2025-01-27 00:00:00	50	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-62eca069-e832-49b5-a37c-889ef52b2b30	MGA
$TkP-bc7a8885-6d16-4ab0-84b9-9688f80ea642	30000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-63e96074-ee58-4688-af06-f914ba7d6a55	MGA
$TkP-72772fbb-9ebf-4a3d-9341-30992fa2ff4c	20000	2025-03-27 00:00:00	400	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-63e96074-ee58-4688-af06-f914ba7d6a55	MGA
$TkP-45a97a7c-654f-47a7-8ae3-87c02fa14762	25000	2025-03-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-47a7235a-faec-492a-94cb-3300238ce56e	MGA
$TkP-14ab1994-0cd4-4416-8a8c-9fb2d7ebbddb	30000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-47a7235a-faec-492a-94cb-3300238ce56e	MGA
$TkP-eafab31f-5004-4b94-9e6f-aaa3dc6393e8	20000	2025-03-27 00:00:00	600	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-47a7235a-faec-492a-94cb-3300238ce56e	MGA
$TkP-5d7e4160-c316-4edb-aad4-39b29cfac084	20000	2025-03-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449	MGA
$TkP-8bc41507-be9a-4b07-aa3d-dcf9a7999905	25000	2025-03-27 00:00:00	200	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449	MGA
$TkP-57f5bd73-98a4-481b-a983-2573e7094d77	15000	2025-03-27 00:00:00	400	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-2e5dc016-edc3-47fb-b194-1af6fe14c449	MGA
$TkP-c6ce548a-0716-438c-b527-d21a7e69e937	25000	2025-03-27 00:00:00	100	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a	MGA
$TkP-2f8c5905-fa03-4f12-a0ee-66f012a7f379	30000	2025-03-27 00:00:00	100	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a	MGA
$TkP-d584a921-ee31-47d1-8d55-c6322484aa00	20000	2025-03-27 00:00:00	200	$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	$Evt-e6b22183-4ad8-497d-966c-e8041fa12c9a	MGA
$TkP-72c04241-f441-44bc-96df-13e6cb262995	10000	2025-03-27 00:00:00	200	$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	$Evt-4f4c6994-cc24-40fe-85ed-6fc738d43999	MGA
$TkP-1ab1d352-cfa4-4874-a6f8-b92263587596	15000	2025-03-27 00:00:00	100	$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	$Evt-4f4c6994-cc24-40fe-85ed-6fc738d43999	MGA
\.


--
-- Data for Name: tickets_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets_type (id, ticket_type, img_path, description) FROM stdin;
$Tik-7805c92b-e80c-46c7-a0b0-48a7867076e1	VIP	/tickets/no-img-yet/	VIP tickets offer exclusive access, premium seating, and additional perks such as backstage passes or meet-and-greet opportunities.
$Tik-2c42cbc6-b35a-4ff3-9ef4-b2bf81655409	STANDARD	/tickets/no-img-yet/	Standard tickets provide general admission to the event with access to all main areas and activities.
$Tik-900f82a7-efeb-42e2-9ec5-fed09bda0ec5	EARLY_BIRD	/tickets/no-img-yet/	Early bird tickets are discounted tickets available for a limited time, offering savings for those who purchase in advance.
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (email, profile_img_path, last_name, first_name, password, user_role, is_active, created_at) FROM stdin;
admin@example.com	src/main/resources/static/assets/image/user/no_image.png	Doe	John	hashed_password_123	ADMIN	t	2025-03-07 16:28:50.306472
tapakila.noreply@gmail.com	src/main/resources/static/assets/image/user/no_image.png	Abegà	Razafindratelo	$2a$10$yC9tZt9UAjrtp9gSvLd8.u4s0mWpst3OiQFhaY7o4vNYVpNWtMS0e	ADMIN	t	2025-03-19 17:16:06.842688
a.razafindratel@gmail.com	src/main/resources/static/assets/image/user/no_image.png	Razafindratelo	Abegà	password	USER	f	2025-03-07 16:34:17.078055
a.razafindratelo@gmail.com	src/main/resources/static/assets/image/user/no_image.png	Abegà	Razafindratelo	$2a$10$wf5jO/sNSVB3TP6tndBn0eHNz.lmKv5j6KrsRm0PpNfAOX1jUSzVe	USER	t	2025-03-20 15:08:54.078128
hei.tsanta.3@gmail.com	?	john	doe	$2a$10$Qsfe0BrjpvkI6Fhd5VY/Ye75sMCc8H2BMFswiggR5xobR45zVDOXO	ADMIN	t	2025-04-02 16:25:48.525936
\.


--
-- Name: ticket_ticket_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ticket_ticket_number_seq', 11, true);


--
-- Name: access_token access_token_access_token_user_email_createdat_expiresat_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.access_token
    ADD CONSTRAINT access_token_access_token_user_email_createdat_expiresat_key UNIQUE (access_token, user_email, created_at, expires_at);


--
-- Name: account_activation account_activation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_activation
    ADD CONSTRAINT account_activation_pkey PRIMARY KEY (id);


--
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- Name: event event_title_description_date_time_time_zone_location_locati_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_title_description_date_time_time_zone_location_locati_key UNIQUE (title, description, date_time, time_zone, location, location_url, image_path, category, status, number_of_ticket, max_ticket_per_user);


--
-- Name: events_category events_category_event_category_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_category
    ADD CONSTRAINT events_category_event_category_key UNIQUE (event_category);


--
-- Name: events_category events_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_category
    ADD CONSTRAINT events_category_pkey PRIMARY KEY (id);


--
-- Name: events_type events_type_event_type_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_type
    ADD CONSTRAINT events_type_event_type_key UNIQUE (event_type);


--
-- Name: events_type events_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_type
    ADD CONSTRAINT events_type_pkey PRIMARY KEY (id);


--
-- Name: payment_mode payment_mode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_mode
    ADD CONSTRAINT payment_mode_pkey PRIMARY KEY (id);


--
-- Name: payment_mode payment_mode_provider_type_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_mode
    ADD CONSTRAINT payment_mode_provider_type_key UNIQUE (provider, type);


--
-- Name: refresh_token refresh_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_pkey PRIMARY KEY (refresh_token);


--
-- Name: refresh_token refresh_token_refresh_token_user_email_createdat_expiresat_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_refresh_token_user_email_createdat_expiresat_key UNIQUE (refresh_token, user_email, created_at, expires_at);


--
-- Name: ticket ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id);


--
-- Name: ticket_price ticket_price_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket_price
    ADD CONSTRAINT ticket_price_pkey PRIMARY KEY (id);


--
-- Name: ticket_price ticket_price_price_id_ticket_type_id_event_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket_price
    ADD CONSTRAINT ticket_price_price_id_ticket_type_id_event_key UNIQUE (price, id_ticket_type, id_event);


--
-- Name: ticket ticket_ticket_number_id_ticket_price_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_ticket_number_id_ticket_price_key UNIQUE (ticket_number, id_ticket_price);


--
-- Name: tickets_type tickets_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets_type
    ADD CONSTRAINT tickets_type_pkey PRIMARY KEY (id);


--
-- Name: tickets_type tickets_type_ticket_type_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets_type
    ADD CONSTRAINT tickets_type_ticket_type_key UNIQUE (ticket_type);


--
-- Name: user user_last_name_first_name_user_role_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_last_name_first_name_user_role_key UNIQUE (last_name, first_name, user_role);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (email);


--
-- Name: access_token access_token_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.access_token
    ADD CONSTRAINT access_token_user_email_fkey FOREIGN KEY (user_email) REFERENCES public."user"(email);


--
-- Name: account_activation account_activation_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_activation
    ADD CONSTRAINT account_activation_user_email_fkey FOREIGN KEY (user_email) REFERENCES public."user"(email);


--
-- Name: creates creates_id_event_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.creates
    ADD CONSTRAINT creates_id_event_fkey FOREIGN KEY (id_event) REFERENCES public.event(id);


--
-- Name: creates creates_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.creates
    ADD CONSTRAINT creates_user_email_fkey FOREIGN KEY (user_email) REFERENCES public."user"(email);


--
-- Name: events_category events_category_id_event_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_category
    ADD CONSTRAINT events_category_id_event_type_fkey FOREIGN KEY (id_event_type) REFERENCES public.events_type(id);


--
-- Name: has_type has_type_id_event_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_type
    ADD CONSTRAINT has_type_id_event_fkey FOREIGN KEY (id_event) REFERENCES public.event(id);


--
-- Name: has_type has_type_id_events_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_type
    ADD CONSTRAINT has_type_id_events_type_fkey FOREIGN KEY (id_events_type) REFERENCES public.events_type(id);


--
-- Name: refresh_token refresh_token_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_user_email_fkey FOREIGN KEY (user_email) REFERENCES public."user"(email);


--
-- Name: ticket ticket_id_payment_mode_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_id_payment_mode_fkey FOREIGN KEY (id_payment_mode) REFERENCES public.payment_mode(id);


--
-- Name: ticket ticket_id_ticket_price_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_id_ticket_price_fkey FOREIGN KEY (id_ticket_price) REFERENCES public.ticket_price(id);


--
-- Name: ticket_price ticket_price_id_ticket_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket_price
    ADD CONSTRAINT ticket_price_id_ticket_type_fkey FOREIGN KEY (id_ticket_type) REFERENCES public.tickets_type(id);


--
-- Name: ticket ticket_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_user_email_fkey FOREIGN KEY (user_email) REFERENCES public."user"(email);


--
-- Name: TABLE access_token; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.access_token TO tapakila_db_admin;


--
-- Name: TABLE account_activation; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.account_activation TO tapakila_db_admin;


--
-- Name: TABLE creates; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.creates TO tapakila_db_admin;


--
-- Name: TABLE event; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.event TO tapakila_db_admin;


--
-- Name: TABLE events_category; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.events_category TO tapakila_db_admin;


--
-- Name: TABLE events_type; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.events_type TO tapakila_db_admin;


--
-- Name: TABLE has_type; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.has_type TO tapakila_db_admin;


--
-- Name: TABLE payment_mode; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.payment_mode TO tapakila_db_admin;


--
-- Name: TABLE refresh_token; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.refresh_token TO tapakila_db_admin;


--
-- Name: TABLE ticket; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ticket TO tapakila_db_admin;


--
-- Name: TABLE ticket_price; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ticket_price TO tapakila_db_admin;


--
-- Name: SEQUENCE ticket_ticket_number_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.ticket_ticket_number_seq TO tapakila_db_admin;


--
-- Name: TABLE tickets_type; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.tickets_type TO tapakila_db_admin;


--
-- Name: TABLE "user"; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public."user" TO tapakila_db_admin;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT SELECT,USAGE ON SEQUENCES  TO tapakila_db_admin;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES  TO tapakila_db_admin;


--
-- PostgreSQL database dump complete
--

