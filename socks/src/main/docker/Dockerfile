FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]

#FROM openjdk:11-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]