FROM maven:3-jdk-11 AS maven_build

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package -DskipTests



FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app/
COPY --from=maven_build /build/target/*.jar /app/
ENTRYPOINT ["java", "-jar", "socks_for_test_task.jar"]