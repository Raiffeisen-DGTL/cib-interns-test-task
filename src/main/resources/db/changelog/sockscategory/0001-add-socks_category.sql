CREATE TABLE IF NOT EXISTS socks_category
(
    id          uuid        NOT NULL,
    color       varchar(20) NOT NULL,
    cotton_part int         NOT NULL,
    CONSTRAINT pk_socks_category PRIMARY KEY (id)
);