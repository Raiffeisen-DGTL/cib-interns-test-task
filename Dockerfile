#base image
FROM openjdk:11
#copy jar from target to image
ADD build/libs/Socks-Service-1.0.jar /app.jar
#port expose
EXPOSE 8080
#start jar inside container
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "app.jar"]