FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY target/socks-api.jar socks-api.jar
ENTRYPOINT ["java", "-jar", "socks-api.jar"]
EXPOSE 8080
