CREATE TABLE socks_batch
(
    id          SERIAL PRIMARY KEY,
    color       VARCHAR NOT NULL,
    cotton_part INTEGER NOT NULL CHECK (0 <= cotton_part AND cotton_part <= 100),
    quantity    INTEGER NOT NULL CHECK (quantity > 0),

    UNIQUE (color, cotton_part)
);
