#!/bin/bash

# Pull new changes
#git pull

# Prepare Jar
mvn clean package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export DB_USERNAME=prod_sock_store_db_user
export DB_PASSWORD=Papa9IgeNiy666GUCCi3mkb
export DB_NAME=sock_store_db
export DB_PORT=3306
#export WEB_PORT=8080

# Start new deployment
docker-compose up --build -d