create table socks
(
    color      varchar(100) not null,
    cottonPart integer      not null,
    quantity   bigint       not null,
    constraint pk_color_cottonpart primary key (color, cottonPart)
);

insert into socks (color, cottonPart, quantity)
values ('red', 100, 1),
       ('black', 2, 1),
       ('yellow', 50, 1),
       ('red', 54, 1),
       ('black', 24, 1),
       ('yellow', 19, 1),
       ('red', 2, 1),
       ('black', 45, 1),
       ('yellow', 64, 1),
       ('black', 23, 1),
       ('testQ100', 23, 100),
       ('testQ', 23, 1);

