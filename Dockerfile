FROM adoptopenjdk:11-jre-hotspot
LABEL maintainer="khromov"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/RaiffeisenAPI-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
