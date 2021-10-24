 create table hibernate_sequence (next_val bigint);
 insert into hibernate_sequence values ( 1 );
 create table socks (
     id integer not null,
      color varchar(255),
       cotton_part bigint,
        quantity bigint,
         primary key (id));