# socks_for_test_task
### Тестовое задание для Raiffeisen Java Bootcamp 2021
[Текст задания](https://github.com/Raiffeisen-DGTL/cib-interns-test-task "Тестовое задание для Java стажеров").

### Стек:
- Java
- Maven
- Spring Boot + Data JPA + Web
- PostgreSQL
- Liquibase
- Docker
- Heroku
___

Тестирование приложения через Swagger доступно по адресу: http://localhost:8080/swagger.html.<br>
___

Для запуска **Docker** контейнера необходимо запустить команду из корневой директории:
```
docker-compose up
```
После инициализации приложения и базы данных доступен Swagger: http://localhost:8082/swagger.html.<br>
БД доступна по адресу: jdbc:postgresql://localhost:15000/postgres (логин и пасс - **postgres**).
___
Также приложение развернуто на **Heroku**: https://socks-for-test.herokuapp.com/swagger.html
