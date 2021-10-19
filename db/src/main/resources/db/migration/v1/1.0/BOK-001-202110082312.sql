CREATE SCHEMA socks_storage;

CREATE TABLE socks_storage.socks
(
    color       VARCHAR(30) NOT NULL,
    cotton_part INTEGER     NOT NULL,
    quantity    INTEGER     NOT NULL,
    PRIMARY KEY (color, cotton_part)
);