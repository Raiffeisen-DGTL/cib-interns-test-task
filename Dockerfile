# Version: 0.0.1
FROM ubuntu:18.04
WORKDIR /work
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y postgresql
RUN apt-get install -y maven
ADD . /work/
RUN mvn clean install
RUN mvn demo:run
#COPY  /pom.xml /
EXPOSE 8080