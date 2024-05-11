create table subscription_to_notifications
(
    id          serial primary key,
    name        varchar(255) not null unique,
    description varchar(255)
);

alter table subscription_to_notifications
    owner to postgres;


insert into subscription_to_notifications (name, description)
values ('UEFA_CHAMPIONS_LEAGUE_MATCHES',
        'Подписка на рассылку уведомлений о предстоящих матчах UEFA champions league');
