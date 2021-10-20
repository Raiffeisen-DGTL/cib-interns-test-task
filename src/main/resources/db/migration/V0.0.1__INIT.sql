CREATE TABLE TEAM
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(100) NOT NULL,
    squad_type VARCHAR(100) NOT NULL,
    tag        VARCHAR(100) NOT NULL,
    CONSTRAINT name_unique UNIQUE (name)
);

CREATE TABLE EMPLOYEE
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    given_name VARCHAR(100) NOT NULL,
    position   VARCHAR(100) NOT NULL,
    age        int          NOT NULL,
    team_id    bigint REFERENCES TEAM (id)
);