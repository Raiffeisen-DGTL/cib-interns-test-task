INSERT INTO Colors (name)
VALUES ('red'),
       ('orange'),
       ('yellow'),
       ('green'),
       ('blue'),
       ('black'),
       ('white'),
       ('pink'),
       ('brown'),
       ('gray'),
       ('purple');

INSERT INTO Socks (cotton_part, quantity, color_id)
VALUES (15, 10, (SELECT id FROM Colors WHERE name = 'black')),
       (90, 99, (SELECT id FROM Colors WHERE name = 'white')),
       (0, 111, (SELECT id FROM Colors WHERE name = 'gray')),
       (100, 8, (SELECT id FROM Colors WHERE name = 'black')),
       (83, 150, (SELECT id FROM Colors WHERE name = 'brown')),
       (34, 40, (SELECT id FROM Colors WHERE name = 'blue')),
       (50, 1000, (SELECT id FROM Colors WHERE name = 'gray'));