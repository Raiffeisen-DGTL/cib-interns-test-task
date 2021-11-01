create table if not exists  socks_stock
(
    count int not null,
    color varchar(64)
        constraint socks_stock_socks_color_color_fk
            references socks_color,
    cotton_part int not null
        constraint socks_stock_socks_cotton_part_part_fk
            references socks_cotton_part,
    stock_id serial
);

create unique index socks_stock_stock_id_uindex
    on socks_stock (stock_id);

alter table socks_stock
    add constraint socks_stock_pk
        primary key (stock_id);
