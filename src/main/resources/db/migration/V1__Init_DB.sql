create table socks
(
    id         long AUTO_INCREMENT PRIMARY KEY,
    color      varchar(255),
    cottonPart int,
    quantity   int
);

insert into socks (color, cottonPart, quantity)
VALUES ('red', 90, 150);
insert into socks (color, cottonPart, quantity)
VALUES ('black', 20, 90);
insert into socks (color, cottonPart, quantity)
VALUES ('green', 50, 33);