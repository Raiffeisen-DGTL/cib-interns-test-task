<H3>Решение задачи.</H3>

Приложение основано на Spring Boot.

Для хранения данных системы используется PostgreSQL.

Для версионирования схемы базы данных используется Liquibase.

Для запуска приложения в Docker-контейнере необходимо в каталоге src/main/docker 
выполнить команду "docker-compose up --build".

Приложение развернуто на Heroku и доступно по адресу: https://accounting-socks.herokuapp.com