#!/bin/bash

echo -e "\e[1;95mlaunch project building"
cd /init-serv/api-test-task
echo -e "\e[1;95mmvn package"
mvn package
echo -e "\e[1;95java -jar .jar"
java -jar /init-serv/api-test-task/target/api-test-task-0.0.1-SNAPSHOT.jar