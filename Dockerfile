FROM gradle:8.1.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/seguradora.jar
ENTRYPOINT ["java", "-jar", "seguradora.jar"]
