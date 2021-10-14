# Тестовое задание для Java стажеров
Приложение основано на SpringBoot Framework.
## База данных 
Использовалась свободная объектно-реляционная система управления базами данных PostgreSQL.
База данных состояла из одной таблицы **pairs_of_socks**. Таблица имела следующий вид:
         
| Столбец       | Тип                |
| :-----------: |:------------------:|
| id            | int                |
| color         | varchar(30)        |
| cottonPart    | int                |
| quantity      | int                |

Запрос для создания таблицы:

```
CREATE TABLE pairs_of_socks 
(
    id SERIAL PRIMARY KEY,
    color VARCHAR(30),
    cottonPart INTEGER,
    quantity INTEGER
);
```
Подразумевается, что в таблице **pairs_of_socks** хранятся пары носков.

## Принцип работы URL HTTP-методов 
1.	При вызове URL HTTP-метода POST /api/socks/income получаем запрос в виде JSON-объекта.

    Далее метод выполняет следующую работу:
    * Проверяет валидность параметров запроса
        - Если невалидны – HTTP 400 
    * Проверяет на наличие пары носков в базе данных
        - Если пара носков с заданными параметрами (color и cottonPart) уже была в базе данных, тогда увеличиваем количество в столбце quantity для строки, отвечающей за эту пару носков - возвращаем HTTP 200 Successfully
        - Если такой пары до этого не было, тогда добавляем ее в БД - возвращаем HTTP 200 Successfully
 >
2.	При вызове URL HTTP-метода POST /api/socks/outcome получаем запрос в виде JSON-объекта.

    Далее метод выполняет следующую работу:

    * Проверяет валидность параметров запроса
      - Если невалидны – HTTP 400 
    * Проверяет на наличие пары носков в базе данных
      - Если пара отсутствует - HTTP 400 These pairs of socks are not in    stock
      - Если количество, которое требуется отгрузить со склада больше, чем имеется на складе - HTTP 400 Unable to send more than there is
      - Если количество, которое собираемся отгрузить меньше или равно, чем есть на складе, то - HTTP 200 Successfully
>
3.	При вызове URL HTTP-метода GET /api/socks получаем параметры в URL.

    Далее метод выполняет следующую работу:
    * Проверяет валидность параметров запроса
        - Если невалидны – HTTP 400
    * Выводит результат, соответствующий параметрам, в теле ответа в виде строкового представления целого числа - HTTP 200 **int**

## Тесты

Таблица в БД имеет вид:

**pairs_of_socks**
| id            | color              | cottonPart | quantity |
| :-----------: |:------------------:|:----------:|:--------:|  
| 1             | yellow             |30          |10        |
| 2             | red                |80          |17        |
| 3             | black              |100         |16        |
| 4             | pink               |55          |44        |
| 5             | yellow             |70          |142       |

### Пример запроса метода GET /api/socks


**/api/socks?color=yellow&operation=moreThan&cottonPart=25**

> Результат: <span style="color:green">HTTP 200,  **152**</span>  *(Две позиции в таблице)*

**/api/socks?color=black&operation= equal&cottonPart=100**

>Результат: <span style="color:green">HTTP 200, **16** </span>

**/api/socks?color=white&operation= equal&cottonPart=100**

>Результат: <span style="color:green">HTTP 200, **0**</span> *(нет такой пары)*


### Пример запроса метода POST /api/socks/outcome:

**/api/socks/outcome**

*JSON объект*
```
{
   "color": "red",
   "cottonPart": 80,
   "quantity": 7
}
```
>Результат: <span style="color:green">HTTP 200 Successfully</span>, в базе данных строка примет вид:

| id            | color              | cottonPart | quantity |
|:-------------:|:------------------:|:----------:|:--------:|
|2              |red                 | 80         |10        |

**/api/socks/outcome**

*JSON объект*
```
{
   "color": "pink",
   "cottonPart": 55,
   "quantity": 100
}
```
Результат: <span style="color:red"> HTTP 400, Unable to send more than there is</span>

### Пример запроса метода POST /api/socks/income

**/api/socks/income**

*JSON объект*
```
{
   "color": "green",
   "cottonPart": 1080,
   "quantity": 88
}
```
>Результат: <span style="color:red">HTTP 400, Invalid input</span> 

**/api/socks/income**

*JSON объект*

```
{
   "color": "black",
   "cottonPart": 100,
   "quantity": 10
}
```
>Результат:<span style="color:green"> HTTP 200 Successfully</span>, в базе данных строка примет вид 

| id            | color              | cottonPart | quantity |
|:-------------:|:------------------:|:----------:|:--------:|
|3              |black               | 100        |26        |