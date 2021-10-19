# socks-problem-raif
<strong>Solution of test problem for raiffeisen bank</strong>
<p>Здравствуйте! Меня зовут Илья Козлов, это моё решение тестовой задачи.</p>

Использованы следующие технологии: Spring Boot, MySQL, Flyway. 
Создан REST API, который может быть запущен:
1) На удалённом сервере heroku
2) В докер-контейнере

<p>Правила, учтённые при создании сервиса:/p>
  <ul>
  <li>Число отпускаемых и приходящих носков не может быть отрицательным</li>
  <li>Число носков на складе не может быть меньше нуля</li>
  <li>Все параметры запросов должны быть указаны</li>
  <li>Нельзя забрать со склада больше носков, чем там имеется</li>
</ul>

<strong>Если приложение запускается в докер-контейнере</strong>
<p>Если приложение запускается в докер-контейнере, то необходим установленный docker и docker-compose. В папке проекта лежит файл docker-compose.yml, который содержит информацию о том, как докер должен запускать приложение. Он состоит из двух частей: базы данных MySQL и приложения Spring Boot. Изображение приложения размещено на dockerhub и доступно для загрузки <a href="https://hub.docker.com/repository/docker/lugalkiennn/j-app/general">https://hub.docker.com/repository/docker/lugalkiennn/j-app/general</a>. Достаточно просто запустить docker-compose, он сам загрузит image и запустит проект. Потребуется немного подождать, так как перед запуском Spring Boot есть задержка, чтобы база данных успешно запустилась. В докере присутствует Flyway, поэтому при запуске подгружаются миграции, заполяется база.</p>
<p>Команда для запуска докер-контейнера:</p>
<code>docker-compose up</code>
<p>После этого с приложением можно общаться с помощью URL-запросов, оно занимает 8080 порт и слушает его. Для этого удобно использовать приложение Postman, так как оно позволяет формировать запросы с телом JSON. Варианты запросов:</p>
<ul>
  <li><p>GET /api/socks</p>
Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса. Пример:
  <a href="http://localhost:8080/api/socks?color=blue&operation=moreThan&cottonPart=30">http://localhost:8080/api/socks?color=blue&operation=moreThan&cottonPart=30</a></li>
 <li><p>POST /api/socks/income</p>
Регистрирует приход носков на склад. Пример:
  <a href="http://localhost:8080/api/socks/income">http://localhost:8080/api/socks/income</a>
  Пример тела:
   <pre><code>
     {
    "color":"brown",
    "cottonPart":50,
    "quantity":50
}
     </code></pre></li>
  <li><p>POST /api/socks/outcome</p>
Регистрирует отпуск носков со склада. Пример:
  <a href="http://localhost:8080/api/socks/outcome">http://localhost:8080/api/socks/outcome</a>
  Пример тела:
   <pre><code>
     {
    "color":"brown",
    "cottonPart":50,
    "quantity":50
}
     </code></pre></li>
</ul>
<p>Описание передаваемых параметров можно посмотреть в тестовом задании.</p>

<strong>Если приложение запускается с удалённого сервера heroku</strong>
<p>Если приложение запускается на удалённом сервере, то делать ничего дополнительного не нужно, оно доступно по ссылке <a href="https://socks-service-kozlov.herokuapp.com">https://socks-service-kozlov.herokuapp.com<a>. Далее следует вписать уже URL наших методов. Они по содержанию повторяют таковые для докер-контейнера.</p>
<p>Для запуска приложения в heroku пришлось отключить Flyway, так как он не поддерживается версией предоставляемой там базы данных, поэтому она была запущена из файла и содержит некоторое количество информации. Варианты запросов:</p>
<ul>
  <li><p>GET /api/socks</p>
Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса. Пример:
  <a href="https://socks-service-kozlov.herokuapp.com/api/socks?color=brown&operation=moreThan&cottonPart=60">https://socks-service-kozlov.herokuapp.com/api/socks?color=brown&operation=moreThan&cottonPart=60</a></li>
 <li><p>POST /api/socks/income</p>
Регистрирует приход носков на склад. Пример:
  <a href="https://socks-service-kozlov.herokuapp.com/api/socks/income">https://socks-service-kozlov.herokuapp.com/api/socks/income</a>
  Пример тела:
   <pre><code>
     {
    "color":"brown",
    "cottonPart":50,
    "quantity":50
}
     </code></pre></li>
  <li><p>POST /api/socks/outcome</p>
Регистрирует отпуск носков со склада. Пример:
  <a href="https://socks-service-kozlov.herokuapp.com/api/socks/outcome">https://socks-service-kozlov.herokuapp.com/api/socks/outcome</a>
  Пример тела:
   <pre><code>
     {
    "color":"brown",
    "cottonPart":50,
    "quantity":50
}
     </code></pre></li>
</ul>
  
  <p>В базе уже содержатся следующие элементы, с которыми можно взаимодействовать (цвет, процент хлопка, число пар):</p>
  <pre><code>
  ('black',50,10),
    ('red',30,20),
    ('green',80,8),
    ('black',80,20),
    ('blue',60,30),
    ('brown',70,100);
    </code></pre>
