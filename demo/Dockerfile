FROM openjdk:14-jdk-alpine
RUN addgroup -S springdocker && adduser -S springdocker -G springdocker
USER springdocker:springdocker
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]