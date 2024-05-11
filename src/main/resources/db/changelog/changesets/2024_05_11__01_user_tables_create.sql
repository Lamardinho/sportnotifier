create table user_details
(
    id           bigserial primary key,
    login        varchar(255)                        not null unique,
    password     varchar(255)                        not null,
    created_time timestamp default CURRENT_TIMESTAMP not null
);


create table user_permissions
(
    id   serial primary key,
    name varchar(255)
);


create table user_has_permissions
(
    user_id       bigint  not null
        constraint fk_user_has_permissions_user_details references user_details,
    permission_id integer not null
        constraint fk_user_has_permissions_user_permissions references user_permissions,
    primary key (permission_id, user_id)
);


create table persistent_logins
(
    id        bigserial primary key,
    username  varchar(255),
    token     varchar(255),
    series    varchar(255),
    last_used timestamp(6)
);
