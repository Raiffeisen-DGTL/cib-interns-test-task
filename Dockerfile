FROM postgres:latest
RUN apt-get --force-yes -y update && apt-get --force-yes -y install openjdk-11-jre
ARG JAR_FILE=*.jar
COPY target/${JAR_FILE} application.jar
EXPOSE 8080
EXPOSE 5432
#ENTRYPOINT ["java", "-jar","/application.jar"]