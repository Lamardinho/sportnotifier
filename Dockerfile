FROM eclipse-temurin:17.0.8_7-jre
EXPOSE 8090
WORKDIR /opt/app
ARG SPRING_PROFILES_ACTIVE=prod
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
