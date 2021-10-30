#!/bin/bash
#
cd /init-serv/cib-interns-test-task-v.2.0.0-rc0/
tree
mvn package
tree
echo "pwd: $(pwd)"
echo -e "\e[1;95mBuildVersion: \e[1;92m""1.0.0-dev0"
java -jar '/init-serv/cib-interns-test-task-v.1.0.0-dev0/target/api-test-task-2.0.0-rc0.jar'
