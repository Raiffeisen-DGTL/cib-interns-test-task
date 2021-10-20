create table socks(
    id              bigserial primary key,
    color           varchar(50) not null,
    cotton_part     int not null,
    quantity        int not null,
    created_at      timestamp default current_timestamp not null,
    updated_at      timestamp default current_timestamp
);

insert into socks (color, cotton_part, quantity)
values
('red', 32, 40),
('black', 70, 30),
('blue', 90, 32),
('white', 99, 100);

