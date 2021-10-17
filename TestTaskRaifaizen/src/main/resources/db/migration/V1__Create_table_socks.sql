create table socks
(
    id          int          not null auto_increment ,
    color       varchar(256) not null,
    cotton_part int          not null,
    quantity    int          not null,
    primary key (id),
    check (cotton_part >= 0 and cotton_part <= 100),
    check (quantity > 0)
);