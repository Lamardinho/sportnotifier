create table if not exists test_liquibase_table
(
    id   bigserial primary key,
    name varchar(50) not null
        constraint uk_1name unique
);
alter table test_liquibase_table
    owner to postgres;
