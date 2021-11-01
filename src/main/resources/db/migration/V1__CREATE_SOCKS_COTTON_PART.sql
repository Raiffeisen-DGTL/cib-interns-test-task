create table if not exists socks_cotton_part
(
    part int not null
);

create unique index socks_cotton_part_part_uindex
    on socks_cotton_part (part);

alter table socks_cotton_part
    add constraint socks_cotton_part_pk
        primary key (part);