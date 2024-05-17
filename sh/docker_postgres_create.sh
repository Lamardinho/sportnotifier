#!/bin/bash

# README
# для запуска, если не хотите палить пароль в терминале:
# перейдите в нужную папку: cd /home/your_user/
# создайте файл: nano docker_postgres_create.sh
# Дайте разрешение на выполнение скрипта с помощью команды chmod: chmod +x docker_postgres_create.sh
# запустите: ./docker_postgres_create.sh

CONTAINER_NAME=postgresql
POSTGRES_USER=postgres_user
POSTGRES_PASSWORD=postgres_password
PORTS=5432:5432
NETWORK_NAME=sportnotifier_network
DATABASE_NAME=sportnotifier
POSTGRES_V=16.0-alpine3.18

# Запуск контейнера PostgreSQL
docker run -d --network $NETWORK_NAME --restart always --name $CONTAINER_NAME -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -p $PORTS postgres:$POSTGRES_V
sleep 5
# Проверка успешного запуска контейнера
if [ $? -eq 0 ]; then
    echo "Контейнер PostgreSQL успешно запущен PORTS: $PORTS."
     # Создание базы данных sportnotifier
        docker exec -i $CONTAINER_NAME psql -U $POSTGRES_USER -c "create database $DATABASE_NAME owner $POSTGRES_USER encoding 'utf-8';"
        if [ $? -eq 0 ]; then
            echo "База данных $DATABASE_NAME успешно создана."
        else
            echo "Ошибка при создании базы данных $DATABASE_NAME."
            sleep 10
            exit 1
        fi
else
    echo "Ошибка при запуске контейнера PostgreSQL."
    sleep 10
    exit 1
fi

sleep 10
