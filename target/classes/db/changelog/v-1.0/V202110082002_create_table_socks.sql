CREATE TABLE IF NOT EXISTS "socks"
(
    id          BIGSERIAL PRIMARY KEY,
    color       VARCHAR(255) NOT NULL,
    cotton_part INTEGER      NOT NULL,
    quantity    INTEGER      NOT NULL
);