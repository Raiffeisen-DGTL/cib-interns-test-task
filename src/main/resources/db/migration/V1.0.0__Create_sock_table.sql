create table Socks (
    id bigserial not null primary key,
    color varchar(128) not null,
    cotton_part int4 not null
        constraint cotton_part_constraint check ( cotton_part >= 0 and cotton_part <= 100),
    quantity bigint not null
       constraint quantity_constraint check ( quantity >= 0 ),
    constraint color_cotton_part_unique UNIQUE(color, cotton_part)
);