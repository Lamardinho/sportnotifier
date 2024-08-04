FROM eclipse-temurin:17.0.12_7-jdk

EXPOSE 8090

WORKDIR /opt/app

ENV SPRING_PROFILES_ACTIVE=local,datasource_h2,ssl

COPY build/libs/*.jar sportnotifier.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "sportnotifier.jar"]
