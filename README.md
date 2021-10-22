# cib-interns-test-task
## Описание
Решенное тестовое задание https://github.com/Raiffeisen-DGTL/cib-interns-test-task

## Инструкция по установке

- Выкачать исходный код из данного репозитория
- Установить docker (https://docs.docker.com/engine/install/)
- Запустить скрипт db-build.sh или выполнить команды: docker pull postgres
  docker run --rm --name pgdocker -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
- Запустить Spring Boot приложение (CibInternsTestTaskApplication.java)

## Endpoints

- POST /socks-store/api/socks/income request body:
```
{
  "color": (String)
  "cottonPart": (int в диапазоне от 0 до 100)
  "quantity": (int больше 0)
}
```
- POST /socks-store/api/socks/outcome
```
{
  "color": (String)
  "cottonPart": (int в диапазоне от 0 до 100)
  "quantity": (int больше 0)
}
```
- GET /socks-store/api/socks
```
  1. color (String)
  2. operation ("moreThan","lessThan","equal")
  3. cottonPart (int в диапазоне от 0 до 100)
```
> "/socks-store" - контекстный путь