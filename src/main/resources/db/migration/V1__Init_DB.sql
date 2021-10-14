drop table if exists socks cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table socks (
                       id int8 not null,
                       color varchar(255),
                       cotton_part int4 not null,
                       quantity int4 not null,
                       primary key (id)
);