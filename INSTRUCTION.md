# Инструкции для запуска Sock Storage Service
### Online
- Ссылка на апи:
http://176.99.11.23:8080/
- Поднял на VPS REG.RU
- Если требуется доступ в бд, то параметры запуска описаны в .env
### Localhost

#### Запуск локально с помощью Docker
Требуются:
- java 11v
- mvn 3.6v
- docker 20.10v, docker-compose 1.29v

#### Инструкции к запуску:
Если есть Makefile:\
`make build` - Билд докера \
`make dev` - Запуск приложения \
Мануально: \
`docker-compose build backend` \
`docker-compose up database backend`

P.S.
Скрипты миграций бд, билда описаны в Makefile, при первом запуске не требуются.
Протестировано на Ubuntu 21.04