create table if not exists test
(
    id   bigserial primary key,
    name varchar(50) not null
        constraint uk_1name unique
);
alter table test
    owner to postgres;

insert into test (name)
values ('test1'),
       ('test2'),
       ('test3');
