#!/bin/bash
# shellcheck disable=SC2164
# Упаковка проекта и отправка в docker hub.
cd ~/IdeaProjects/_pet/sportnotifier

log() {
    local edited_string="***************** $1 *****************"
    echo "" && echo "$edited_string" && echo ""
}

IMAGE_NAME="slezkin23/sportnotifier"
VERSION="1.7.6"

error_handler() {
    sleep 300
}
trap 'error_handler' ERR

log "BUILD PROJECT" && ./gradlew clean build

log "BUILDX MY-BUILDER"
docker buildx rm mybuilder
docker buildx create --name mybuilder --driver docker-container --use
docker buildx inspect mybuilder --bootstrap

log "BUILDING ALL TAGS" && docker buildx build --platform linux/amd64,linux/arm64 -t $IMAGE_NAME:latest -t $IMAGE_NAME:$VERSION --push .

log "ALL BUILDING COMPLETE"
sleep 60
