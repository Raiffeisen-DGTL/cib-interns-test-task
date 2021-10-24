DROP TABLE sock;

CREATE TABLE sock
(
    color text NOT NULL,
    cotton_part INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    UNIQUE(color, cotton_part)
);