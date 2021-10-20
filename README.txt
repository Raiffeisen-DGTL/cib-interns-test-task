Описание приложения:

Приложение упаковано в docker image. 

Для удобства запуска используется embedded H2 Database с автоматическим накатыванием SQL DDL скриптов с помощью библиотеки flyway.

Инструкция по установке:
Для запуска приложения необходим установленный docker
Команда для скачивания:

docker pull ste5an/docker-sber-app:sberapp

Команда для запуска:

docker run -p 8080:8080 ste5an/docker-sber-app:sberapp

Ссылка на Докер репозиторий:

https://hub.docker.com/r/ste5an/docker-sber-app

--------------------------------------------------------------------------------------------
SQL DDL scripts:

CREATE DATABASE sbertestDB;

CREATE TABLE TEAM (
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(100) NOT NULL,
    squad_type VARCHAR(100) NOT NULL,
    tag        VARCHAR(100) NOT NULL
);

CREATE TABLE EMPLOYEE (
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    given_name VARCHAR(100),
    position   VARCHAR(100),
    age        int         ,
    team_id    bigint REFERENCES TEAM (id)
);

--------------------------------------------------------------------------------------------

Пример использования REST API

1. Создание команды:
POST http://localhost:8080/rest/team/save

{
    "name": "C++",
    "type": "BackEnd",
    "tag": "CB01"
}

2. Создание нового employee с указанием teamName для привязки к нужной команде:
POST http://localhost:8080/rest/employee/save
{
  "teamName": "C++",
  "firstName": "Peter",
  "lastName": "Levin",
  "givenName": "D",
  "position": "Middle C++",
  "age": 34
}
 
3. Остальные команды должны быть интуитивно понятны для выполнения, но если возникнут вопросы, то буду рад ответить.


Swagger описание REST API
http://localhost:8080/swagger-ui.html

TEAM COMMANDS
----------------------------------------------------------------------------------------------
Save new team:      
POST http://localhost:8080/rest/team/save
{
        "name": "C++",
        "type": "BackEnd",
        "tag": "CB01"
}

Find all teams:
GET http://localhost:8080/rest/team/teams
Delete team by id:
DELETE http://localhost:8080/rest/team/delete/?
Find team by id:
GET http://localhost:8080/rest/team/find/?
Update team by id:
PUT http://localhost:8080/rest/team/update

----------------------------------------------------------------------------------------------
EMPLOYEE COMMANDS
----------------------------------------------------------------------------------------------
Save new employee:      
POST http://localhost:8080/rest/employee/save
{
    "teamName": "C++",
    "firstName": "Peter",
    "lastName": "Levin",
    "givenName": "D",
    "position": "Middle C++",
    "age": 34
}
Find all employees:     
GET http://localhost:8080/rest/employee/employees
Delete employee by id:     
DELETE http://localhost:8080/rest/employee/delete/{id}
Find employee by id:    
GET http://localhost:8080/rest/employee/find/{id}
Update employee by id:
PUT http://localhost:8080/rest/employee/update
