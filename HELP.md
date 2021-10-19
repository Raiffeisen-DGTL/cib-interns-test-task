# Сервис для учета носков на складе

Сервис использует Spring Boot (MVC, GraphQL, Swagger, Data (postgres)).

###Доступ к сервису доступен через: 

* [REST](https://raif-demo.herokuapp.com/)
* [GraphQL](https://raif-demo.herokuapp.com/graphiql?path=/graphql)

### Документация
* [swagger-ui](https://raif-demo.herokuapp.com/swagger-ui/)

### Порядок запуска:
* запустить бд docker-compose up
* запустить приложение mvn spring-boot:run (или использовать запуск через IDE)

### Deploy в heroku
* установить/настроить heroku-cli
* создать приложение
* создать бд
* выполнить build-and-run.sh <имя_созланного проекта>