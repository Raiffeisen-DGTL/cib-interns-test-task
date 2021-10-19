create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );



CREATE TABLE socks (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      color varchar(255),
                      cotton_part int not null,
                      quantity int not null,
                      PRIMARY KEY (id)
) engine=MyISAM;