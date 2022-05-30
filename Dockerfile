FROM alpine

WORKDIR /app

RUN apk add openjdk11

COPY build/libs/socks-api.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]