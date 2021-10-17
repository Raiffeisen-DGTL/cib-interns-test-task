CREATE TABLE IF NOT EXISTS socks
(
    id      SERIAL PRIMARY KEY ,
    color   VARCHAR(200) NOT NULL ,
    cotton_part    integer  NOT NULL
);