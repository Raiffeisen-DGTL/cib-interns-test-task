FROM openjdk:17-jdk-alpine

WORKDIR /repo
COPY . /repo
ENTRYPOINT ["./gradlew", "bootRun"]
EXPOSE 8080
