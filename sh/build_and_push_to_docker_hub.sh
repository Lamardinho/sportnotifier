# shellcheck disable=SC2164
# Упаковка проекта и отправка в docker hub.
cd ~/IdeaProjects/sportnotifier

log() {
    local edited_string="***************** $1 *****************"
    echo "" && echo "$edited_string" && echo ""
}
AMD_TAG="slezkin23/sportnotifier:amd64"
ARM_TAG="slezkin23/sportnotifier:arm64"
SPRING_PROFILE="prod"

log "BUILD PROJECT" && ./gradlew clean build
log "BUILD AMD" && docker build -t $AMD_TAG --build-arg SPRING_PROFILES_ACTIVE=$SPRING_PROFILE .
log "PUSH FOR AMD" && docker push $AMD_TAG
log "BUILD ARM" && docker buildx build --platform linux/arm64  -t $ARM_TAG --build-arg SPRING_PROFILES_ACTIVE=$SPRING_PROFILE .
log "PUSH ARM" && docker push $ARM_TAG
log "ALL BUILDING COMPLETE"

sleep 100
