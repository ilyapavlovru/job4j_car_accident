create table accident
(
    id   serial primary key,
    name varchar(255)
);

create table rule
(
    id   serial primary key,
    name varchar(255)
);

create table item_category
(
    accident_id       integer not null
        constraint accident_sub_id_fk
            references accident(id),
    rule_id integer not null
        constraint rule_sub_id_fk
            references rule(id)
);