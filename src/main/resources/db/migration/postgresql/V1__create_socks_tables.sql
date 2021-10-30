create sequence if not exists cotton_parts_seq start 1 increment 1;
create sequence if not exists sock_colors_seq start 1 increment 1;
create sequence if not exists sock_seq start 1 increment 1;

create table if not exists cotton_parts(
    id                  int8 not null,
    cotton_part int4 not null,
    primary key (id)
);

create table if not exists sock_colors
(
    id    int8         not null,
    color varchar(255) not null,
    primary key (id)
);

create table if not exists socks
(
    id             int8 not null,
    quantity       int4 not null,
    cotton_part_id int8,
    sock_color_id  int8,
    primary key (id)
);

alter table if exists socks
    add constraint cotton_parts_fk foreign key (cotton_part_id) references cotton_parts;

alter table if exists socks
    add constraint sock_color_fk foreign key (sock_color_id) references sock_colors;