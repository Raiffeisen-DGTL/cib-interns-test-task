FROM openjdk:11-jdk-alpine
EXPOSE 8080

MAINTAINER baratov
COPY target/warehouse-0.0.1-SNAPSHOT.jar warehouse-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/warehouse-0.0.1-SNAPSHOT.jar"]