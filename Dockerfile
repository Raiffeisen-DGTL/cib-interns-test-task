# syntax=docker/dockerfile:1
FROM gradle:7.2-jdk17 AS BUILD
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon -x test

FROM amazoncorretto:17-alpine-jdk
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=BUILD /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]