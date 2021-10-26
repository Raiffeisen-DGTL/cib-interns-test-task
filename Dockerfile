FROM openjdk:11-ea-11-jre-slim
COPY build/libs/Raiff-0.0.1-SNAPSHOT.jar socks_app
ENTRYPOINT ["java", "-jar", "socks_app"]
