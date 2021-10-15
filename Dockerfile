FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /web.jar
ENTRYPOINT ["java","-jar","/web.jar"]