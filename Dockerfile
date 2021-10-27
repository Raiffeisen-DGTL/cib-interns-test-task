FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/socks-store-0.0.1-SNAPSHOT.jar /socks-store-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "socks-store-0.0.1-SNAPSHOT.jar"]

