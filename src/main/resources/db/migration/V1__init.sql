CREATE TABLE socks_pair (
    id SERIAL PRIMARY KEY,
    color VARCHAR(100) NOT NULL CHECK (length(color) > 0),
    cotton_part SMALLINT NOT NULL CHECK (socks_pair.cotton_part >= 0 and socks_pair.cotton_part <= 100)
);