FROM openjdk:11-ea-11-jre-slim
ARG JAR=/Raiff-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
#COPY build/libs/Raiff-0.0.1-SNAPSHOT.jar socks-mysql.jar
COPY ${JAR} socks_app
ENTRYPOINT ["java", "-jar", "socks_app"]
