# syntax=docker/dockerfile:1
FROM openjdk:8-jdk
ARG dbUser
ARG dbPassword
EXPOSE 8080:8080
RUN mkdir /app
RUN mkdir /app/src
COPY build.gradle.kts /app
COPY settings.gradle.kts /app
COPY gradle.properties /app
COPY ./src /app/src
COPY gradlew /app
COPY ./gradle /app/gradle
WORKDIR /app
RUN chmod +x gradlew
RUN ./gradlew clean shadowJar
WORKDIR /app/build/libs
RUN echo "#!/bin/bash \n java -jar cib-interns-test-task-1.0-all.jar -P:ktor.database.user=${dbUser} -P:ktor.database.password=${dbPassword} -P:ktor.database.url=db/postgres?currentSchema=postgres" > entrypoint.sh
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]
