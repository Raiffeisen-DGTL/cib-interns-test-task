#!/usr/bin/env bash
./mvnw package
cp target/socks-0.0.1-SNAPSHOT.war services/app
docker-compose build
docker-compose up
