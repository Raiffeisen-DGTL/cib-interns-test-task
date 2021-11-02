#!/bin/bash
#
echo -e "\e[1;92mStart entrypoint.sh"
tree /init-serv && tree /root/.serv
cd /init-serv
wget https://github.com/niki75jr/cib-interns-test-task/archive/refs/tags/v.3.0.0-rc0.tar.gz -O - | tar -xz
cd /init-serv/cib-interns-test-task-v.3.0.0-rc0/
echo -e "\e[1;91m $(tree)"
echo "pwd: $(pwd)"
echo -e "\e[1;95mBuildVersion: \e[1;92m""3.0.0-rc0"
mvn package
tree /init-serv
java -jar '/init-serv/cib-interns-test-task-v.3.0.0-rc0/target/api-test-task-3.0.0-rc0.jar'
