#!/bin/bash

# Pull new changes
#git pull

# Prepare Jar
mvn clean package -Dmaven.test.skip=true

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export DB_USERNAME=VetirDoIt
export DB_PASSWORD=Papa9IgeNiy666GUCCi3mkb
export DB_NAME=sock_store_db

# Start new deployment
docker-compose -f docker-compose.yml up --build -d