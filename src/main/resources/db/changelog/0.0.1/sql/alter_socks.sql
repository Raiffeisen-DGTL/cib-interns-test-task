create sequence socks_seq start 1;

create table if not exists socks
(

    id         bigint      not null default (nextval('socks_seq')),
    color      varchar(35),
    cotton_part integer check (cotton_part >= 0 and cotton_part <=100),
    quantity INTEGER check ( quantity >= 0)
);

alter table socks
    add constraint socks_pk primary key (id);

alter table socks
    add constraint socks_uq unique (id);

comment on table socks is 'Носки';
comment on column socks.id is 'Идентификатор носков';
comment on column socks.color is 'Цвет носков';
comment on column socks.cotton_part is 'Содержание хлопка в процентах';
comment on column socks.quantity is 'Количество пар носков';
