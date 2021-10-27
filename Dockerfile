FROM openjdk:8

WORKDIR /app

ADD target/SocksApi-0.0.1-SNAPSHOT.jar .

CMD ["java", \
"-Duser.timezone=GMT+3:00", \
"-jar", "SocksApi-0.0.1-SNAPSHOT.jar"]