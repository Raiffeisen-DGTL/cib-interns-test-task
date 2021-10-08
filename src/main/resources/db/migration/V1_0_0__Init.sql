CREATE TABLE IF NOT EXISTS socks(
    id serial,
    color varchar(32) NOT NULL,
    cotton_part smallint NOT NULL DEFAULT 0,
    quantity integer NOT NULL DEFAULT 0,
    PRIMARY KEY(id, color, cotton_part)
);