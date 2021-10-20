CREATE TABLE SOCKS_STORAGE
(
    id          BIGSERIAL PRIMARY KEY                                NOT NULL,
    color       text                                                 NOT NULL,
    cotton_part smallint
        CONSTRAINT cotton_part CHECK (cotton_part between 0 and 100) NOT NULL,
    quantity    BIGINT                                               NOT NULL check (quantity >= 0)
);