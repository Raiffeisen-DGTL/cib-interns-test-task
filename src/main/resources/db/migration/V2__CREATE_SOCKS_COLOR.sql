create table if not exists  socks_color
(
    color varchar(64) not null
);

create unique index socks_color_color_uindex
    on socks_color (color);

alter table socks_color
    add constraint socks_color_pk
        primary key (color);
