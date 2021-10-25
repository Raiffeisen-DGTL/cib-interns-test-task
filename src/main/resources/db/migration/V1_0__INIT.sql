create sequence hibernate_sequence start 1 increment 1;
-- исправить сиквенс

CREATE TABLE socks (
    id int primary key,
    color varchar(25) not null,
    cotton_part int not null,
    quantity int not null
);