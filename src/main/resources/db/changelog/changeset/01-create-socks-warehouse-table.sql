create table if not exists socks_warehouse
(
    id serial primary key,
    color varchar(50) not null,
    cotton_part integer,
    available_quantity integer not null
);