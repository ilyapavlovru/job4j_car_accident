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

create table accident_rule
(
    accident_id       integer not null
        constraint accident_sub_id_fk
            references accident(id),
    rule_id integer not null
        constraint rule_sub_id_fk
            references rule(id)
);