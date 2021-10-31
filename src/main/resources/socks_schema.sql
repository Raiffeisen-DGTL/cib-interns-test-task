create table socks
(
    id          int8 generated always as identity,
    color       varchar(255),
    cotton_part int8,
    quantity    int8,
    primary key (id)
);

create unique index tab_uq on socks (color, cotton_part);
