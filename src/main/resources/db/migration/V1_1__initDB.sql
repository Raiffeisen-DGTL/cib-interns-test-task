drop table if exists socks cascade;

create table socks (
    color varchar(255) not null,
    cotton_part int2 not null,
    quantity int4 not null,
    primary key (color, cotton_part)
    );