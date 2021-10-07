# syntax=docker/dockerfile:1

FROM maven:3.8.2-jdk-11 AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11
RUN addgroup --system raif_intern && adduser --system raif_intern --ingroup raif_intern
USER raif_intern:raif_intern
COPY --from=builder /app/target/*.jar /build/app.jar
ENTRYPOINT java -jar /build/app.jar