FROM openjdk:11-jre-slim
COPY build/libs/raiffeisen_test_task.jar raiffeisen_test_task.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/raiffeisen_test_task.jar"]