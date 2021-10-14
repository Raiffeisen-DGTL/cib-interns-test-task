FROM openjdk:17-jdk-alpine
EXPOSE 8080

MAINTAINER baratov
COPY target/accountingOfSocks-0.0.1-SNAPSHOT.jar accountingOfSocks-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/accountingOfSocks-0.0.1-SNAPSHOT.jar"]

#docker build -t springio/gs-spring-boot-docker .КОПИРОВАТЬ
 #Эта команда создает изображение и маркирует его как springio/gs-spring-boot-docker.

# version: '3.2'
# services:
#   mysql_db:
#     image: mysql:latest
#     environment:
#       MYSQL_ROOT_PASSWORD: secret
#       MYSQL_DATABASE: messanger
#     ports:
#       - "3306:3306"
#docker rmi hello-world:latest удалить images указываем REPOSITORY:TEG

#=======Запуск Postgree c нужными параметрами ========
#docker ps -a Список всех контейнеров
#docker run --name habr-pg-13.3 -p 5435:5432 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=accounting_for_socks -d postgres:13.3