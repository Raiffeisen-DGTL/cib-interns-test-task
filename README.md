# cib-interns-test-task
Тестовое задание для Java стажеров от  Raiffeisen DGTL, полный текст задания можно найти в файле task.md или по ссылке: https://github.com/Raiffeisen-DGTL/cib-interns-test-task.
Суть задания - реализовать приложение для автоматизации учёта носков на складе магазина.
# Описание реализации:
Для выполнения задания использовались SpringBoot 2.5.4, Java 11 и сборщик Maven.

Для сборки проекта через консоль можно вызвать из корневой папки проекта команду **mvn compile**, а для запуска **mvn spring-boot:run**. Для запуска через среду разработки необходимо использовать класс **KeyValueApplication**.

Проект работает с использованием базы данных H2 в in-memory режиме, для версионирования схемы базы данных используется Flyway. Написанная для него первая миграция (src/main/resources/db/migration/V1__first_migration.sql) создает таблицу для учета носков и добавляет в нее несколько записей:

![](https://github.com/devkabezrooki/cib-interns-test-task/blob/main/db.png)

Также реализована возможность упаковать приложение в докер-контейнер.

## Работа с сервисом:

Проект запускается по адресу 127.0.0.1 и порту 8090 либо по адресу ```https://devkabezrooki-raiffeisen-api.herokuapp.com/```, после чего можно обращаться к описанным ниже методам.

### POST /api/socks/income
Регистрирует приход носков на склад.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

* color — цвет носков, строка из перечня: "black", "white", "red", "orange", "yellow", "green", "blue", "purple", "pink", "brown", "grey", "multi";
* cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100;
* quantity — количество пар носков, целое число больше 0.

При вводе некорректного значения возвращает сообщение об ошибке и код HTTP 400, при ошибке, не зависящей от вызывающей стороны возвращает код HTTP 500. Если метод сработал корректно, возвращает сообщение "Ok" и код HTTP 200.

Примеры запроса:
*https://devkabezrooki-raiffeisen-api.herokuapp.com/api/socks/income?color=fghfh&cottonPart=20&quantity=15
Вернул сообщение: "Uncorrect color, you must choose from:[black, white, red, orange, yellow, green, blue, purple, pink, brown, grey, multi]" и статус 400Bad Request.
