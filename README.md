# Socks-Accounting

### Тестовое задание на позицию Java стажера

### Особенности проекта:
:white_check_mark: Основан на Spring Boot Framework с использованием системы сборки Maven    
:white_check_mark: Размещен в открытом доступе на Heroku: https://sock-accounting-system.herokuapp.com    
:white_check_mark: Использует PostgreSQL в качестве БД    
:white_check_mark: Подключен flyway для версионирования схемы БД (в директории /src/main/resources/db/migration/ можно увидеть исходную схему БД)    

### API:
`/api/socks/income` - POST request to add socks

Example of JSON:

```
{
    "color": "red",
    "cottonPart": 35,
    "quantity": 500
}
```

`/api/socks/outcome` - POST request to reduce socks

Example of JSON:

```
{
    "color": "black",
    "cottonPart": 20,
    "quantity": 100
}
```

`/api/socks` - GET request to get count of socks by params

Params:

+ color
+ operation: "moreThan", "lessThan", "equal"
+ cottonPart


Request example:    
`https://sock-accounting-system.herokuapp.com/api/socks?color=black&operation=lessThan&cottonPart=90`

