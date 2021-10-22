FROM gradle:7.2.0-jdk17 AS build
COPY . /home/gradle/
RUN gradle clean assemble

FROM adoptopenjdk:14.0.2_12-jre-hotspot-bionic
WORKDIR /app
COPY --from=build /home/gradle/build/libs/socks*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]