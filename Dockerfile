FROM fabric8/java-alpine-openjdk11-jdk as builder
WORKDIR app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract
FROM fabric8/java-alpine-openjdk11-jdk
RUN apk --no-cache add curl
WORKDIR app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
