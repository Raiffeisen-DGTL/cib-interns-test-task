FROM openjdk:8-jdk-alpine
VOLUME /web-app
ARG JAR_FILE="target/SocksService-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} socks-app.jar
ENTRYPOINT ["java","-jar","socks-app.jar"]
EXPOSE 8080