CREATE TABLE public.sock_colors
(
    id bigint NOT NULL,
    color character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT sock_colors_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.sock_colors
    OWNER to postgres;

CREATE TABLE public.transaction_types
(
    id bigint NOT NULL,
    transaction_type character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT transaction_types_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.transaction_types
    OWNER to postgres;

CREATE TABLE public.socks_transactions
(
    id bigint NOT NULL,
    color_id bigint,
    transaction_type_id bigint,
    quantity integer,
    CONSTRAINT socks_transactions_pkey PRIMARY KEY (id),
    CONSTRAINT fk_color_id FOREIGN KEY (color_id)
        REFERENCES public.sock_colors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_transaction_type_id FOREIGN KEY (transaction_type_id)
        REFERENCES public.transaction_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.socks_transactions
    OWNER to postgres;
