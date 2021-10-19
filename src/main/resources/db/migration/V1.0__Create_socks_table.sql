
CREATE TABLE socks (
                       color varchar(25),
                       cotton_part int,
                       quantity int,
                       PRIMARY KEY (color,cotton_part)
) ;
INSERT INTO j_db_socks.socks (color,cotton_part,quantity)
VALUES
    ('black',50,10),
    ('red',30,20),
    ('green',80,8),
    ('black',80,20),
    ('blue',60,30),
    ('brown',70,100);