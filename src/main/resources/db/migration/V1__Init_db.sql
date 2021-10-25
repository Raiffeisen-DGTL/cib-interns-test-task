create table socks (
    id bigint not null auto_increment,
    color varchar(255),
    cotton_part integer not null,
    quantity integer not null,
    primary key (id))
engine=InnoDB