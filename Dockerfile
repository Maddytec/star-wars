FROM gradle:7.2.0-jdk16 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build -x test --no-daemon

FROM openjdk:16-jdk
EXPOSE 8080
COPY --from=build /app/build/libs/*SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar","/app/app.jar"]