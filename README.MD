I did not succeeded deploying on Heroku and creating docker-compose.yml, so my server only works locally.

To make sure that your database is running, try `lsof -i :5432`, you should get something like that:

```bash 
[shamil@shamil] $ lsof -i :5432
COMMAND   PID   USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
java    12043 shamil   80u  IPv6 116694      0t0  TCP localhost:58970->localhost:postgresql (ESTABLISHED)
```

How to launch my application:

- make sure that you have a running PostgreSQL database with database name: `postgres`, user: `postgres`,
  pass: `postgres`.
- run `./mvnw spring-boot:run`

Queries that I tested locally on my machine:

```bash
curl http://localhost:8080/api/socks/all; echo 
curl -v -H "Content-Type: application/json" -d  '{"color":"blue","cottonPart":5,"quantity":5}' http://localhost:8080/api/socks/income; echo 
curl "http://localhost:8080/api/socks?color=blue&operation=moreThan&cottonPart=3"; echo
`curl -v -H "Content-Type: application/json" -d  '{"color":"blue","cottonPart":5,"quantity":5}' http://localhost:8080/api/socks/outcome; echo`
```

I did not succeed in launching with docker-compose, I have written the docker-compose.yml, but the spring boot can not
request to database. Maybe some day I will fix that :)

- `docker-compose.yml`

```bash
version: '3'

services:
  db:
    image: postgres:latest
    restart: unless-stopped
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_DB=postgres
      - POSTGRES_PASSWORD=postgres
    ports:
      - "5432:5432"
    expose:
      - 8080

  app:
    depends_on:
      - db
    links:
      - db
    build: ./RESTServer
    restart: on-failure
    ports:
      - "8080:8080"
```

