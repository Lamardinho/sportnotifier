FROM amazoncorretto:21.0.4

EXPOSE 8090

WORKDIR /opt/app

ENV SPRING_PROFILES_ACTIVE=local,datasource_h2,ssl

COPY build/libs/*.jar sportnotifier.jar

# Проверка существования JAR-файла
RUN if [ -f /opt/app/sportnotifier.jar ]; then echo "JAR file exists"; else echo "JAR file does not exist"; exit 1; fi

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "sportnotifier.jar"]
