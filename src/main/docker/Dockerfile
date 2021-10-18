FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application12.jar
ENTRYPOINT ["java", "-jar", "application12.jar"]