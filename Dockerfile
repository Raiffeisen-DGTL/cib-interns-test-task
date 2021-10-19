FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE="target/task-0.0.1.jar"
WORKDIR /
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
