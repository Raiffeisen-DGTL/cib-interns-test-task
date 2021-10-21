CREATE TABLE pair_of_socks
(
    id          SERIAL
        CONSTRAINT socks_id_pk PRIMARY KEY,
    color       VARCHAR NOT NULL,
    cotton_part int     NOT NULL,
    quantity    int     NOT NULL
);

CREATE UNIQUE INDEX socks_unique_color_cotton_part_index
    ON pair_of_socks (color, cotton_part);