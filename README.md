# Тестовое задание для Райффайзенбанк

# Как запустить приложение
Для того, что бы запустить проект, нужно, либо через idea запусить main в классе: ```ru.raiffeisen.soksapp.SoksappApplication```

Либо через maven выполить команду:
```
mvn spring-boot:run
```
приложение будет запущено на порте 8080

# Поддерживаемые операции

1. Добавление новых носков

Для добавления новых носков необходимо выполнить POST с json запрос формата:
```json
{
    "color": "red",
    "cottonPart": "100",
    "quantity": "5"
}
```
на URL ```http://localhost:8080/api/socks/income```

Пример запроса:
```
Request method:	POST
Request URI:	http://localhost:8080/api/socks/income
Proxy:			<none>
Request params:	<none>
Query params:	<none>
Form params:	<none>
Path params:	<none>
Headers:		Accept=*/*
Content-Type=application/json
Cookies:		<none>
Multiparts:		<none>
Body:
{
"color": "red",
"cottonPart": "100",
"quantity": "5"
}
```
2. Удаление носков 

Для удаления новых носков необходимо выполнить POST с json запрос формата:
```json
{
    "color": "red",
    "cottonPart": "100",
    "quantity": "5"
}
```
на URL ```http://localhost:8080/api/socks/outcome```

Пример запроса:
```
Request method:	POST
Request URI:	http://localhost:8080/api/socks/outcome
Proxy:			<none>
Request params:	<none>
Query params:	<none>
Form params:	<none>
Path params:	<none>
Headers:		Accept=*/*
				Content-Type=application/json
Cookies:		<none>
Multiparts:		<none>
Body:
{
    "color": "red",
    "cottonPart": "100",
    "quantity": "5"
}
```
3. Поиск носков:

Для поиска носков нужно сделать GET запрос с параметрами 
   1. color - цвет носков
   2. operation - тип операции:
      1. lessThan - шерсти меньше чем
      2. moreThan - шерсти больше чем
      3. equals -   шерсти равное количество
   3. cottonPart - количество шерсти

Пример запроса:
```html
http://localhost:8080/api/socks?color=red&operation=lessThan&cottonPart=85
```
Пример ответа:
```json
{
    "count": 10
}
```

## Интеграционные тесты
Интеграционные тесты, которые проверяют базовые операции можно найти в классе:
```SoksappApplicationTests```

