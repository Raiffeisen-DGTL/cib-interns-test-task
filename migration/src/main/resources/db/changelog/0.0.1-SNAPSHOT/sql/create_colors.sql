create sequence if not exists colors_id_seq
    start 1;

create table if not exists colors
(
    id bigint not null default nextval('colors_id_seq' :: regclass),
    color text not null,
    constraint colors_id_pk
        primary key (id)
);

comment on table colors is 'Информация об ассортименте цветов для носоков';

comment on column colors.id is 'Идентификатор цвета';
comment on column colors.color is 'Название цвета';