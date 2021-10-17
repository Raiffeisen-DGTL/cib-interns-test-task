CREATE TABLE socks (
    color varchar(255),
    cotton_part int CHECK ( cotton_part >= 0 AND cotton_part <= 100 ) NOT NULL ,
    quantity int CHECK ( quantity >= 0 ) NOT NULL ,
    PRIMARY KEY (color, cotton_part)
);
