FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/suck-1.0.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]