FROM tomcat:9.0.41-jdk15-openjdk
COPY target/cib-interns-test-task-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["tomcat.sh","run"]