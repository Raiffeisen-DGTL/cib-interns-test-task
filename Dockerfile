FROM adoptopenjdk:16-jdk-hotspot
ARG JAR_FILE=target/*.jar
ENV SPRING_DATASOURCE_URL=aaaaaa
ENV SPRING_DATASOURCE_USERNAME=bbbbb
ENV SPRING_DATASOURCE_PASSWORD=ccccc
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.datasource.url=${SPRING_DATASOURCE_URL}", "-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}", "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", "-jar", "app.jar"]