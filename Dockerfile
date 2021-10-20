FROM openjdk:11
ARG JAR_FILE=*.jar
COPY target/RaifService-0.0.1-SNAPSHOT.jar RaifService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "RaifService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080