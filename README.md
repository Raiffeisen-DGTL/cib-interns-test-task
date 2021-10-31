Добрый день. Задание выполнил. Проект задеплоин на Heroku – url: https://socks-application-postgres.herokuapp.com . Дальше /api/socks и если нужно добавить приход носков, то POST(запрос) - /income , если списать то - /outcome . Тело состоит из json страницы и имеет формат: 
{
    "color" : "red",
    "cottonPart" :70,
    "quantity" : 10
}

GET(запрос) – url: https://socks-application-postgres.herokuapp.com . Дальше /api/socks и параметры запроса. Например: ?color=red&operation=moreThan&cottonPart=10
Все как было сказано по заданию. Тестировалось в POSTMAN.


