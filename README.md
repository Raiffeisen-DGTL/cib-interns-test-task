# Raiffeisen-Spring-Boot-Test

Тестовое задание - https://github.com/Raiffeisen-DGTL/cib-interns-test-task

## Используемый стек:
Java 11, PostgreSQL, Liquibase, SpringBoot, Spring Data Jpa, Maven, Swagger, Lombok

## Подробнее:
### Для запуска приложения требуется иметь PostgreSQL на устройстве.
1. Создать базу данных под названием "raiffeisen_db"
2. В папке application.properties на месте этих строк вставить свои значений логина и пароля от БД, если это необходимо:
   ###### spring.datasource.username=${Свое значение}
   ###### spring.datasource.password=${Свое значение}
3. Приложение находится на порте 8081, измените при надобности:
   ###### server.port=${Свое значение}
4. Таким же образом в папке liquibase.properties в той же директории измените password и username на собственные.
4. Запустите приложение :)

## К проекту подключен Swagger API, рабочий URL адрес к нему:
###### Учитывайте номер порта при обращении к адресу, если поменяли его в application.properties
http://localhost:8081/api/swagger-ui.html#/
> Можете не обращать внимания на него, если нет необходимости пользования

### К сожалению, не успел разобраться с докером и развернуть БД в докер-контейнере :(
