drop table if exists  sock;
create table sock(
    id serial primary key,
    color varchar (50) not null ,
    cotton_part decimal not null,
    quantity integer
);
insert into sock(color, cotton_part, quantity)
values
       ('BLACK',75,24),
       ('BLACK',25,12),
       ('BLACK',50,10),
       ('BLACK',10,5),
       ('BLACK',5,20),
       ('RED',10,4),
       ('RED',20,7),
       ('RED',50,10),
       ('RED',80,10),
       ('RED',5,20),
       ('BLUE',5,20),
       ('BLUE',10,10),
       ('BLUE',60,2),
       ('GREEN',60,2),
       ('GREEN',20,2),
       ('GREEN',30,2),
       ('GREEN',50,2),
       ('WHITE',50,2),
       ('GREEN',70,2),
       ('GREEN',10,2),
       ('GREEN',30,2);

