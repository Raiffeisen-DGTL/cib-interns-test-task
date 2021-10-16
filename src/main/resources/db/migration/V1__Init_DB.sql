create table socks (
       id serial not null,
       color varchar(255) not null,
       cotton_part int4 not null,
       quantity int4 not null,
       primary key (id)
)