#!/bin/bash

# Pull new changes
#git pull

# Prepare Jar
mvn clean package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export DB_USERNAME=VetirDoIt
export DB_PASSWORD=Papa9IgeNiy666GUCCi3mkb
export DB_NAME=sock_store_db
export DB_PORT=3306

# Start new deployment
docker-compose -f  up --build -d