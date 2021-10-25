DROP TABLE IF EXISTS SOCK CASCADE;

CREATE TABLE SOCK
(
    color       VARCHAR(50) NOT NULL,
    cotton_part INT         NOT NULL CHECK(cotton_part BETWEEN 0 AND 100),
    quantity    INT         NOT NULL CHECK(quantity >= 0),

    CONSTRAINT sock_pkey PRIMARY KEY (color, cotton_part)
);