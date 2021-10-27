# syntax=docker/dockerfile:1
FROM openjdk:11
ADD socks.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]