FROM gradle:7.1-jdk11 AS builder
WORKDIR /app
COPY . /app
RUN  gradle bootJar

FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/build/libs/socks-storage-raiffeisenbank-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]
