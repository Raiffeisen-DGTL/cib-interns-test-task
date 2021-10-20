--liquibase formatted sql

--changeset c0dered:1
--comment: initial schema

--comment: hibernate id generation sequences
CREATE SEQUENCE public.hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.socks_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

--comment: tables
CREATE TABLE public.colors
(
    name varchar(32) NOT NULL,
    CONSTRAINT name_pkey PRIMARY KEY (name),
    CONSTRAINT name_uk UNIQUE (name)
);

CREATE TABLE public.socks
(
    id          bigint      NOT NULL,
    color_name  varchar(32) NOT NULL,
    cotton_part smallint    NOT NULL,
    quantity    int         NOT NULL,
    CONSTRAINT socks_pkey PRIMARY KEY (id),
    CONSTRAINT color_id_fk FOREIGN KEY (color_name)
        REFERENCES public.colors (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT color_name_cotton_part_uk UNIQUE (color_name, cotton_part)
);