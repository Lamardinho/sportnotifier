create table telegram_subscriber
(
    id                       bigserial primary key,
    chat_id                  bigint                              not null unique,
    user_name                varchar(255),
    active                   boolean,
    first_name               varchar(255),
    last_name                varchar(255),
    date_creation            timestamp default CURRENT_TIMESTAMP not null,
    date_last_subscription   timestamp default CURRENT_TIMESTAMP not null,
    date_last_unsubscription timestamp default CURRENT_TIMESTAMP not null
);
