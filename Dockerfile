FROM eclipse-temurin:17.0.11_9-jdk
# for arm64. в 17.0.11_9-jdk есть проблема
#FROM eclipse-temurin:17.0.10_7-jdk

EXPOSE 8090

WORKDIR /opt/app

ARG SPRING_PROFILES_ACTIVE=prod

COPY build/libs/*.jar sportnotifier.jar

ENTRYPOINT ["java", "-jar", "sportnotifier.jar"]
