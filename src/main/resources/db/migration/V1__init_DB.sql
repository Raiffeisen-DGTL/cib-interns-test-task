create table socks
(
    id          bigserial not null,
    color       varchar(255),
    cotton_part int4,
    quantity    int4,
    primary key (id)
)