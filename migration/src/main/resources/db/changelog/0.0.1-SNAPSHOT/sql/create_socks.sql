create sequence if not exists socks_id_seq
    start 1;

create table if not exists socks
(
    id bigint not null default nextval('socks_id_seq' :: regclass),
    color_id bigint not null,
    cotton_part int not null,
    quantity int not null,
    constraint socks_id_pk
        primary key (id),
    constraint socks_color_id
        foreign key (color_id)
        references colors (id)
);

comment on table socks is 'Информация о носках на складе магазина';

comment on column socks.id is 'Идентификатор товара (пары носков определенного типа)';
comment on column socks.color_id is 'Идентификатор цвета носков - ссылка на таблицу COLORS';
comment on column socks.cotton_part is 'Процентное содержание хлопка в составе носков';
comment on column socks.quantity is 'Количество пар носков на складе магазина';