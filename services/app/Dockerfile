FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=./socks-0.0.1-SNAPSHOT.war
WORKDIR /app
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.war"]