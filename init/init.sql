create table socks
(
    id          bigint   AUTO_INCREMENT                          not null primary key,
    color       varchar    not null,
    cotton_part integer    not null,
    quantity    integer    not null,
    PRIMARY KEY (id)
);

alter table socks
    owner to postgres;