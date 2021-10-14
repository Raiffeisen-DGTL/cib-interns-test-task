# Тестовое задание для Java стажеров

Привет!

Мы ищем стажера, который в перспективе станет Junior Java-разработчиком в нашей команде.
Чтобы понять, что мы подходим друг другу, предлагаем вам написать простое web-приложение. Такое задание поможет нам понять, что вы:

* можете понимать поставленную задачу;
* умеете находить необходимую техническую информацию для реализации решения;
* просто умеете кодить.

На задание у вас уйдет ориентировочно один-два вечера. Главное условие — решение должно быть написано с использованием платформы JVM. Библиотеки и фреймворки можно выбирать на свой вкус.

## Что нужно сделать

Реализовать приложение для автоматизации учёта носков на складе магазина. Кладовщик должен иметь возможность:

* учесть приход и отпуск носков;
* узнать общее количество носков определенного цвета и состава в данный момент времени.

Внешний интерфейс приложения представлен в виде HTTP API (REST, если хочется).

## Список URL HTTP-методов

### POST /api/socks/income

Регистрирует приход носков на склад.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

* color — цвет носков, строка (например, black, red, yellow);
* cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
* quantity — количество пар носков, целое число больше 0.

Результаты:

* HTTP 200 — удалось добавить приход;
* HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
* HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

### POST /api/socks/outcome

Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные, но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.

### GET /api/socks/count

Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.

Параметры запроса передаются в URL:

* color — цвет носков, строка;
* operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;
* cottonPart — значение процента хлопка в составе носков из сравнения.

Результаты:

* HTTP 200 — запрос выполнен, результат в теле ответа в виде строкового представления целого числа;
* HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
* HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

Примеры запросов:

* /api/socks/count?color=red&operation=moreThan&cottonPart=90 — должен вернуть общее количество красных носков с долей хлопка более 90%;
* /api/socks/count?color=black&operation=lessThan?cottonPart=10 — должен вернуть общее количество черных носков с долей хлопка менее 10%.

Для хранения данных системы можно использовать любую реляционную базу данных. Схему БД желательно хранить в репозитории в любом удобном виде.

## Как это сделать

Мы ждем, что решение будет:

* написано на языке Java;
* standalone - состоять из одного выполняемого компонента верхнего уровня;
* headless - без UI;
* оформлено как форк к репозитарию и создан пул реквест.

Будет плюсом, если:

* приложение будет основано на Spring(Boot) Framework;
* для версионирования схемы базы данных будет использоваться Liquibase или Flyway;
* база данных будет подниматься рядом с приложением в докер-контейнере;
* приложение будет развернуто на любом облачном сервисе, например Heroku, и его API будет доступно для вызова.
