
CREATE TABLE socks(

    id serial,
    color character varying(20) NOT NULL,
    cottonpart INTEGER NOT NULL,
    quantity INTEGER  NOT NULL
);

INSERT INTO socks (color,cottonpart,quantity)
VALUES
('blue', 15, 50),
('green', 25, 60),
('purple', 35, 70),
('yellow', 45, 80),
('yellow', 45, 10),
('yellow', 15, 40),
('black', 95, 150);


