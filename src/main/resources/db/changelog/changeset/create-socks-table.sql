--liquibase formatted sql

--changeset Andrey:create-chat-socks-table

create table SOCKS
(
    ID          serial primary key,
    COLOR       text not null,
    COTTON_PART int  not null check (COTTON_PART >= 0 and COTTON_PART <= 100),
    QUANTITY    int  not null
);