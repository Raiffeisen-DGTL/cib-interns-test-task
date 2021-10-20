CREATE SCHEMA IF NOT EXISTS public;
create table IF NOT EXISTS socks (
                       id serial primary key ,
                       color VARCHAR(256)  not null,
                       cotton_part int not null,
                       quantity int not null
);