### Описание
Это репозиторий с решением тестового задания. Приложение доступно 
по адресу https://socks-accounting-app.herokuapp.com/.

### Инструкция по запуску на локальной машине
* создание базы данных
```bash
docker build -t socks-db ./
docker run -d --name socks-db-container -p 5432:5432 socks-db
```
* подготовка базы данных (схема расположена в ```/src/main/resources/db/migration/V1_0__create_sock_schema.sql```)
```bash
mvn flyway:migrate -Dflyway.configFiles=flyway.conf
```
* запуск приложения
```bash
mvn spring-boot:run
```
После этого приложение доступно по адресу http://localhost:5000.
