#!/bin/bash
# shellcheck disable=SC2164
# Упаковка проекта и отправка в docker hub.
cd ~/IdeaProjects/sportnotifier

log() {
    local edited_string="***************** $1 *****************"
    echo "" && echo "$edited_string" && echo ""
}

IMAGE_NAME="slezkin23/sportnotifier"
VERSION="1.5.0"
SPRING_PROFILE="prod"

log "BUILD PROJECT" && ./gradlew clean build

log "BUILDX MY-BUILDER"
docker buildx create --name mybuilder --use || echo "Docker Buildx уже создан"
docker buildx inspect mybuilder --bootstrap

log "BUILDING ALL TAGS" && docker buildx build --platform linux/amd64,linux/arm64 \
--build-arg SPRING_PROFILES_ACTIVE=$SPRING_PROFILE -t $IMAGE_NAME:latest -t $IMAGE_NAME:$VERSION --push .

log "ALL BUILDING COMPLETE"
sleep 60
