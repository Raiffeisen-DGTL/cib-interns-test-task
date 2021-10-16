create table spring_boot.socks
(
    color       varchar(255)      not null,
    cotton_part numeric default 0 not null
        constraint socks_cotton_check
            check (cotton_part >= (0)::numeric),
    quantity    numeric default 0 not null
        constraint socks_quantity_check
            check (quantity >= (0)::numeric),
    id          integer generated always as identity
        constraint socks_pkey
            primary key,
    constraint color_cotton_unique
        unique (color, cotton_part)
);

alter table spring_boot.socks
    owner to postgres;

INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('black', 90, 100);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('black', 80, 200);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('yellow', 85, 50);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('brown', 70, 30);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('gray', 80, 50);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('red', 85, 40);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('blue', 95, 25);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('white', 80, 50);
INSERT INTO spring_boot.socks (color, cotton_part, quantity) VALUES ('white', 95, 100);