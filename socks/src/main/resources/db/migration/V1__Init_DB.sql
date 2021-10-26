CREATE TABLE Colors
(
    id   bigserial PRIMARY KEY,
    name text      NOT NULL UNIQUE
);

CREATE TABLE Socks
(
    id          bigserial PRIMARY KEY,
    cotton_part integer   NOT NULL CHECK (cotton_part >= 0 AND cotton_part <= 100),
    quantity    bigint    NOT NULL CHECK (quantity >= 0),
    color_id    bigint    NOT NULL REFERENCES Colors (id)
);