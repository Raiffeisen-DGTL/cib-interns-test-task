create table socks(
    id bigserial primary key,
    color varchar(30) not null,
    cotton_part integer not null check ( cotton_part >= 0 and cotton_part <= 100 ),
    quantity bigint not null check ( quantity >= 0 ),
    constraint color_cottonPart_unique UNIQUE(color, cotton_part)
);