FROM openjdk:17
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} raif-test-task.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "raif-test-task.jar"]