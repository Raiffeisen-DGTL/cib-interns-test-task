# RaiffeisenTask
## Тестовое задание на стажировку Java Bootcamp 2021.

Текст задания можно найти [тут](https://github.com/Raiffeisen-DGTL/cib-interns-test-task "Тестовое задание для Java стажеров").

## Стек технологий
- Java
- Maven
- Spring Boot
- Spring Data JPA
- Liquibase 
- Apache Derby

## Установка
Сервис доступен по адресу https://sillyseal.systems/raiffeisen-task/
**UPD: На данный момент сервис отключен**
Ниже описаны варианты установки локальной версии.

Вы можете скачать исполняемый jar-файл [тут](https://github.com/mickeytheseal/cib-interns-test-task/releases/download/v1.0.0/raiffeisentask-1.0.0-RELEASE.jar "raiffeisentask-1.0.0-RELEASE.jar") и запустить через:
```sh
java -jar <pathToFile>
```

Используя Docker:
```sh
docker pull sillyseal/raiffeisen-task
docker run -d -p 8080:8080 sillyseal/raiffeisen-task
```

Используя Maven:
```sh
git clone https://github.com/mickeytheseal/raiffeisentask.git
cd <yourGitClonePath>
mvnw spring-boot:run
```

## Использование
##### При обращении к https://sillyseal.systems/raiffeisen-task/ 
Получить все содержимое склада:
```sh
GET https://sillyseal.systems/raiffeisen-task/api/socks/all
```
Получить общее количество носков на складе, соответствующих переданным в параметрах критериям запроса:
```sh
GET https://sillyseal.systems/raiffeisen-task/api/socks?color=black&operation=moreThan&cottonPart=30
```
Зарегестрировать поступление носков:
```sh
POST https://sillyseal.systems/raiffeisen-task/api/socks/income?color=red&cottonPart=75&quantity=15
```
Зарегестрировать отпуск носков:
```sh
POST https://sillyseal.systems/raiffeisen-task/api/socks/outcome?color=red&cottonPart=75&quantity=6
```

##### При локальной устновке
Получить все содержимое склада:
```sh
GET http://localhost:8080/api/socks/all
```
Получить общее количество носков на складе, соответствующих переданным в параметрах критериям запроса:
```sh
GET http://localhost:8080/api/socks?color=black&operation=moreThan&cottonPart=30
```
Зарегестрировать поступление носков:
```sh
POST http://localhost:8080/api/socks/income?color=red&cottonPart=75&quantity=15
```
Зарегестрировать отпуск носков:
```sh
POST http://localhost:8080/api/socks/outcome?color=red&cottonPart=75&quantity=6
```
## Коллекции Postman
Скачать коллекцию для тестирования https://sillyseal.systems/raiffeisen-task/ можно [тут](https://github.com/mickeytheseal/cib-interns-test-task/releases/download/v1.0.0/RaiffeisenTaskDeployed.postman_collection "RaiffeisenTaskDeployed.postman_collection").  
Скачать коллекцию для тестирования локальной версии можно [тут](https://github.com/mickeytheseal/cib-interns-test-task/releases/download/v1.0.0/RaiffeisenTaskLocal.postman_collection "RaiffeisenTaskLocal.postman_collection").

## Обратная связь
e-mail: mickeytheseal@gmail.com  
telegram: t.me/sillyseal

