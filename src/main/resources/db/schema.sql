create table if not exists socks (
    id serial primary key,
    color varchar(255),
    cotton_part int,
    quantity int
);