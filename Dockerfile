FROM eclipse-temurin:17.0.11_9-jdk
# FROM eclipse-temurin:17.0.10_7-jdk for arm64
EXPOSE 8090
WORKDIR /opt/app
ARG SPRING_PROFILES_ACTIVE=prod
COPY build/libs/*.jar sportnotifier.jar
ENTRYPOINT ["java", "-jar", "sportnotifier.jar"]
