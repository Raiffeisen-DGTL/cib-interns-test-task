create table color (name varchar(255) not null, primary key (name))
ENTER

create table socks_order (id uuid not null, cotton_part int2, quantity int4, color_name varchar(255), primary key (id))
ENTER

alter table if exists socks_order add constraint FKdsb5omu1gm8gia59mhffkvl57 foreign key (color_name) references color
ENTER

insert into color (name) values ('red');
insert into color (name) values ('black');
insert into color (name) values ('white');
insert into color (name) values ('green');
insert into color (name) values ('blue');
insert into color (name) values ('yellow');
insert into color (name) values ('pink');
insert into color (name) values ('grey');

ENTER