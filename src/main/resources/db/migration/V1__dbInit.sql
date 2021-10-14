create table roles (
    id serial primary key,
    name varchar(100) not null
);

create table users (
    id serial primary key,
    name varchar(150) not null,
    password varchar(12) not null,
    activate bool not null
);

create table socks (
    id serial primary key,
    color varchar(50),
    quantity int,
    operation varchar(20),
    cotton_part int
);

create table users_roles(
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

insert into roles (name) values ('STOREKEEPER');
insert into users (name, password, activate) values ('Maxim.Isaev', '12332112', true);
insert into users_roles (user_id, role_id) values (1, 1);
