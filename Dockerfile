FROM eclipse-temurin:17.0.10_7-jdk
EXPOSE 8090
WORKDIR /opt/app
ARG SPRING_PROFILES_ACTIVE=prod
COPY build/libs/*.jar sportnotifier.jar
ENTRYPOINT ["java", "-jar", "sportnotifier.jar"]
