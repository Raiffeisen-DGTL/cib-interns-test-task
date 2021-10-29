#!/bin/bash
#
cd /init-serv/cib-interns-test-task-v.1.0.0-rc0/
mvn package
java -jar /init-serv/cib-interns-test-task-v.1.0.0-rc0/target/api-test-task-0.0.1-SNAPSHOT.jar
