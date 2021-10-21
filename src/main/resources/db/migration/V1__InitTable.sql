CREATE TABLE IF NOT EXISTS socks (
    id BIGSERIAL PRIMARY KEY,
    color VARCHAR(30) NOT NULL,
    cotton_part INT NOT NULL,
    quantity INT NOT NULL
);