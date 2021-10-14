# test-raif

### Предположительно все пункты [задания](https://github.com/Raiffeisen-DGTL/cib-interns-test-task) выполнил:

* миграции с Flyway
* можно всё поднимать через докер, для этого написаны разные `profiles`
  * нужно запустить скрипт `./build.sh`, он билдит проект maven'ом и создает докер имадж нашего сервиса
  * поднять на докере композитник `./docker/compose.yaml` из нашего сервиса и бд
* задеплоил приложение на aws, доступ по url: http://testraif-env.eba-mtapm35t.eu-central-1.elasticbeanstalk.com/

Запросы на api можно кидать через `./storage/http-requests/sock-requests.http`, только url у них нужно поправлять, т.к. в некоторых обращение к aws, в других к локально поднятому приложению
