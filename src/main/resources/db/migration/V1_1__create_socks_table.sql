CREATE TABLE socks
(
    id              UUID            NOT NULL PRIMARY KEY,
    color           VARCHAR(50)     NOT NULL,
    cotton_part     SMALLINT        NOT NULL,
    created_at      TIMESTAMP       NOT NULL
);