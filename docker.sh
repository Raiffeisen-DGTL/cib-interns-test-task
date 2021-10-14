#!/bin/bash

sudo docker stop socks-postgres
sudo docker rm socks-postgres
sudo docker image prune -f

sudo docker build -t socks-postgres-image ./
sudo docker run -d --name socks-postgres -p 5432:5432 socks-postgres-image

sleep 1

#gradle flywayBaseLine
#gradle flywayMigrate

#sudo docker cp ./dump.sql pg_test:/docker-entrypoint-initdb.d/dump.sql
#sudo docker exec -u postgres socks-postgres psql postgres socks-user -f docker-entrypoint-initdb.d/socks_schema.sql
