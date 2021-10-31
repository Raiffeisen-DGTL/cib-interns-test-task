FROM bellsoft/liberica-openjdk-alpine-musl:11
ARG JAR_FILE=/socks-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]