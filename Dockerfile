FROM eclipse-temurin:17.0.8_7-jre
EXPOSE 8090
WORKDIR /opt/app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# КОМАНДЫ:

#билдим проект:
#./gradlew clean build

#собираем образ:
#docker build -t sportnotifier:latest .

#запускаем контейнер:
#docker run -it --name sportnotifier_v1 -p 8090:8090 -d sportnotifier

# проверяем:
#http://localhost:8090/hello
