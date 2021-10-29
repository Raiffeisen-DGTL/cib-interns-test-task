#!/bin/bash
#
cd /init-serv/cib-interns-test-task-v.1.0.0-rc1/
tree
mvn package
tree
echo "pwd: $(pwd)"
echo -e "\e[1;95mBuildVersion: \e[1;92m""1.0.0-rc1"
java -jar '/init-serv/cib-interns-test-task-v.1.0.0-rc1/target/api-test-task-0.0.1-SNAPSHOT.jar'
