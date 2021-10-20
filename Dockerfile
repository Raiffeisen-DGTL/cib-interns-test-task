FROM openjdk:11

ADD target/CIB-internship-0.0.1-SNAPSHOT.jar CIB-internship-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "CIB-internship-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080