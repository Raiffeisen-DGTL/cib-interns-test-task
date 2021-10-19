# Описание

TestTaskSockService сервис для автоматизации учёта носков на складе магазина. Кладовщик имеет возможность:

* учесть приход и отпуск носков;
* узнать общее количество носков определенного цвета и состава в данный момент времени.

Внешний интерфейс приложения представлен в виде REST.
## Техническая часть
* приложение основано на Spring Boot
* используется СУБД postgreSQL
* для версионирования схемы базы данных будет использоваться Flyway
* приложение развернуто на Heroku

## Список URL HTTP-методов

Для просмотра на Heroku: *http://socksservice.herokuapp.com/swagger-ui.html#/sock-controller*  

При запуске приложения локально: *http://localhost:8081/swagger-ui.html#/sock-controller*

![](https://github.com/Aleshawork/TestTaskSockService/blob/master/src/main/resources/api-doc.png)
