--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

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
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: socks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.socks (
    id integer NOT NULL,
    color character varying(255) NOT NULL,
    cotton_part integer,
    quantity integer,
    CONSTRAINT socks_cotton_part_check CHECK (((cotton_part >= 0) AND (cotton_part <= 100))),
    CONSTRAINT socks_quantity_check CHECK ((quantity >= 0))
);


ALTER TABLE public.socks OWNER TO postgres;

--
-- Name: socks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.socks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.socks_id_seq OWNER TO postgres;

--
-- Name: socks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.socks_id_seq OWNED BY public.socks.id;


--
-- Name: socks id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socks ALTER COLUMN id SET DEFAULT nextval('public.socks_id_seq'::regclass);


--
-- Data for Name: socks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.socks (id, color, cotton_part, quantity) FROM stdin;
1	black	50	17
2	black	30	10
\.


--
-- Name: socks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.socks_id_seq', 2, true);


--
-- Name: socks socks_color_cotton_part_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socks
    ADD CONSTRAINT socks_color_cotton_part_key UNIQUE (color, cotton_part);


--
-- Name: socks socks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socks
    ADD CONSTRAINT socks_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

