create table if not exists socks_cotton_part
(
    part int not null
);

create unique index socks_cotton_part_part_uindex
    on socks_cotton_part (part);

alter table socks_cotton_part
    add constraint socks_cotton_part_pk
        primary key (part);

create table if not exists socks_color
(
    color varchar(64) not null
);

create unique index socks_color_color_uindex
    on socks_color (color);

alter table socks_color
    add constraint socks_color_pk
        primary key (color);

create table if not exists socks_stock
(
    count       int not null,
    color       varchar(64)
        constraint socks_stock_socks_color_color_fk
            references socks_color,
    cotton_part int not null
        constraint socks_stock_socks_cotton_part_part_fk
            references socks_cotton_part,
    stock_id    serial
);

create unique index socks_stock_stock_id_uindex
    on socks_stock (stock_id);

alter table socks_stock
    add constraint socks_stock_pk
        primary key (stock_id);
