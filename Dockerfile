FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8080
ADD /target/cib-interns-test-task.jar cib-interns-test-task.jar
ENTRYPOINT ["java", "-jar", "cib-interns-test-task.jar"]