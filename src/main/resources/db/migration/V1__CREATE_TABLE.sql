create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );

create table socks
(
    id          bigint  not null auto_increment,
    color       varchar(255) not null,
    cotton_part integer not null,
    quantity    integer not null,
    primary key (id)
) engine=MyISAM;
