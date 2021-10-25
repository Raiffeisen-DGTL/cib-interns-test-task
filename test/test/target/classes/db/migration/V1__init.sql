CREATE TABLE socks (
    id serial,
    color text NOT NULL,
    cotton int NOT NULL,
    quantity int NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO socks (color, cotton, quantity)
VALUES
('Red', 40, 3),
('Black', 90, 19),
('White', 20,1),
('Black', 40, 13),
('Black', 67,3);




