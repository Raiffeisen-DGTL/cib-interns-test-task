FROM maven:3.6.0-jdk-11-slim AS builder
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package -DskipTests

FROM openjdk:11
COPY --from=builder /home/app/target/cib-interns-test-task-*0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
