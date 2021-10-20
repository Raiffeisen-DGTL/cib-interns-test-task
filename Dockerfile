FROM openjdk:11-jdk

WORKDIR /cib-interns-test-task
COPY . .
RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]