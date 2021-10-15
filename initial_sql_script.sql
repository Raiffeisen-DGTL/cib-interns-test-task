create database socks_management;

\c socks_management;

create table socks
(
    id serial not null
        constraint socks_pk
            primary key,
    color       varchar(255),
    cotton_part serial,
    quantity    bigint
);

alter table socks
    owner to postgres;


