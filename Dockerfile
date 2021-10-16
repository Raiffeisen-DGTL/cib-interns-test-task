FROM adoptopenjdk/openjdk11:ubi
LABEL maintainer="devkabezrooki"
VOLUME /tmp
EXPOSE 8099
ARG JAR_FILE=target/RaiffeisenAPI-0.0.1-SNAPSHOT.jar 
COPY ${JAR_FILE} app.jar  
ENTRYPOINT ["java","-jar","app.jar"]  