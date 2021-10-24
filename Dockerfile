FROM adoptopenjdk/openjdk11:alpine-jre

COPY /build/libs/cib-interns-test-task-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]