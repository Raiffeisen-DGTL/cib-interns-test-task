FROM openjdk:8-jdk-alpine
LABEL maintainer="author@nezhov"
VOLUME /main-app
EXPOSE 8080
CMD ["java", "-jar","target/InternTask-0.0.1-SNAPSHOT.jar"]


