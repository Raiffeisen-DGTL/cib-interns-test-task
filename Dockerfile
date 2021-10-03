FROM gradle:7.2.0-jdk11-openj9 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/Raiffeisen-test-task-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]