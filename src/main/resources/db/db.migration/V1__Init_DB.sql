create sequence socks_sequence start 1 increment 1

create table socks_request (
      id int8 not null,
      color varchar(255) not null,
      cotton_part int4 not null check (cotton_part<=100 AND cotton_part>=0),
      quantity int4 not null,
      primary key (id)
     )

