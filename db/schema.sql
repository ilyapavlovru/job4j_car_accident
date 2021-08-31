create table accident
(
    id          serial primary key,
    name        varchar(255)                  not null,
    address     varchar(255)                  not null,
    car_number  varchar(30)                   not null,
    description varchar(1000)                 null,
    type_id     int                           not null references accident_type (id),
    status      varchar(30) default 'Принята' not null
);

create table rule
(
    id   serial primary key,
    name varchar(255) not null
);

create table accident_type
(
    id   serial primary key,
    name varchar(255) not null
);

CREATE TABLE authorities
(
    id        serial primary key,
    authority varchar(50) not null unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$v0C5DXq7VSEgnf1iBHxujONhRJAxcPqzpSzLo6AGf3MBNd8Niy/6O',
        (select id from authorities where authority = 'ROLE_ADMIN'));
