create sequence socks_id_seq increment 50;

create table socks
(
    id int default nextval('socks_id_seq') primary key ,
    color varchar not null ,
    cotton_part int check ( cotton_part >= 0 and cotton_part <= 100),
    quantity int check ( quantity >= 0 ),
    created_at timestamp,
    updated_at timestamp,
    version int
);

create unique index cotton_and_color on socks(cotton_part, color);
