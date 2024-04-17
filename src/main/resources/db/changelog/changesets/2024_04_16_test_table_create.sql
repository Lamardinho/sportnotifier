create table if not exists test
(
    id   bigserial primary key,
    name varchar(50) not null
        constraint uk_1name unique
);
alter table test
    owner to postgres;
