# cib-interns-test-task
Тестовое задание для Java стажеров от  Raiffeisen DGTL, полный текст задания можно найти в файле task.md или по ссылке: https://github.com/Raiffeisen-DGTL/cib-interns-test-task.
Суть задания - реализовать приложение для автоматизации учёта носков на складе магазина.
# Описание реализации:
Для выполнения задания использовались SpringBoot 2.5.4, Java 11 и сборщик Maven.

Для сборки проекта через консоль можно вызвать из корневой папки проекта команду **mvn compile**, а для запуска **mvn spring-boot:run**. Для запуска через среду разработки необходимо использовать класс **KeyValueApplication**.

Проект работает с использованием базы данных H2 в in-memory режиме, для версионирования схемы базы данных используется Flyway. Написанная для него первая миграция (src/main/resources/db/migration/V1__first_migration.sql) создает таблицу для учета носков и добавляет в нее несколько записей. !(https://github.com/devkabezrooki/cib-interns-test-task/blob/main/db.png)
