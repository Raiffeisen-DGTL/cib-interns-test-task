--
-- PostgreSQL database dump
--

-- Dumped from database version 10.18
-- Dumped by pg_dump version 10.18

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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: socks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.socks (
    color character varying(255) NOT NULL,
    cottonpart smallint NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT cottonpart_check CHECK (((cottonpart >= 0) AND (cottonpart <= 100))),
    CONSTRAINT quantity_check CHECK ((quantity >= 0))
);


ALTER TABLE public.socks OWNER TO postgres;

--
-- Name: socks socks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socks
    ADD CONSTRAINT socks_pkey PRIMARY KEY (color, cottonpart);


--
-- PostgreSQL database dump complete
--

