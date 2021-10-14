CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE sock
(
    id          BIGINT       NOT NULL,
    color       VARCHAR(400) NOT NULL,
    cotton_part INTEGER      NOT NULL,
    quantity    INTEGER      NOT NULL,
    CONSTRAINT pk_sock PRIMARY KEY (id)
);

CREATE INDEX sock_color_cotton_part_idx ON sock (color, cotton_part);