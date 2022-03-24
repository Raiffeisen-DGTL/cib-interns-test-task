create sequence hibernate_sequence start 1 increment 1;

create table sock_stock (
    id int8 not null,
    color varchar(255),
    cotton_part int2 check (cotton_part<=100 AND cotton_part>=0),
    quantity int8,
    primary key (id))