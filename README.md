# Raiffeisen App

## Развертывание приложения:
1. Для запуска приложения вам понадобиться установленный Docker. Как начать использовать докер можете посмотреть на https://www.docker.com/get-started. 
2. Склонируйте себе репозиторий в папку raiffeisen_app
    ```bash
    $ git clone https://github.com/GrigoriyVoronin/raiffeisen_app.git raiffeisen_app
    ```
3. Перейдите в склонирвоанный репозиторий
    ```bash
    $ cd raiffeisen_app
    ```
4. Используйте docker-compose для запуска контейнера с приложением и БД
    ```bash
    $ sudo docker-compose build
    $ sudo docker-compose up
    ```
5. Приложение доступно по адресу http://localhost:8080
6. При первом запуске вам может понадобиться создать таблицу Socks, исопльзуйте ручку **/api/socks/create_table**