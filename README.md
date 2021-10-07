# Тестовое задание Raiffeisen Bootcamp
## Кратко о проделанной работе
Я создал простое web приложение с REST api внешним интерфейсом. Для решения задачи был использован фреймворк Spring Boot, система контроля базы данных PostgreSQL и Docker для удобного развертывания приложения. Приложение и база данных завернуты в Docker контейнеры и развернуты на облачном сервере. Написаны JUnit тесты для слоя контроллера и бизнес логики приложения.

## Как развернуть
  git clone https://github.com/RussianRightWing/RaiffeisenTask.git
  
  cd RaiffeisenTask/
  
  docker-compose up -d
  
## Как протестировать

  POST: http://77.232.41.95:8080/api/socks/income

  POST: http://77.232.41.95:8080/api/socks/outcome

  GET:  http://77.232.41.95:8080/api/socks?color=red&operation=moreThan&cottonPart=80
  
