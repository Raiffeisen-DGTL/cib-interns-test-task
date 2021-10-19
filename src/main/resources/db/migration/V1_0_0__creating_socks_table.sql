create table socks(
                      id int primary key not null ,
                      color varchar(255) not null,
                      cotton_part int not null,
                      quantity bigint not null,
                      created_at timestamp not null
);