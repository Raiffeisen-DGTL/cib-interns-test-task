FROM openjdk:11
EXPOSE 8080
ADD build/libs/Raiff-0.0.1-SNAPSHOT.jar socks-mysql.jar
ENTRYPOINT ["java", "-jar", "socks-mysql.jar"]
