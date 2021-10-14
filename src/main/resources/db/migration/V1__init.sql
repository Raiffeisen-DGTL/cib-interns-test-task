CREATE TABLE socks (
    id SERIAL PRIMARY KEY,
    color VARCHAR(100) NOT NULL CHECK (length(color) > 0),
    cotton_part SMALLINT NOT NULL CHECK (cotton_part >= 0 and cotton_part <= 100),
    quantity INTEGER NOT NULL CHECK (quantity >= 0)
);