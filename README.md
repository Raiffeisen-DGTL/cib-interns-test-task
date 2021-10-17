# Socks Application by Andrew Pavlov
### Description
It's a RESTful application implemented using Spring Boot and Maven.
### DB 
Embedded H2 DB with the file data storing (src/main/resources/db/sample), contains 1 table Socks
### DB schema
Schema could be found in the liquibase changelog (src/main/resources/db/changelog/db.changelog-master.yaml)
### Docker
Dockerfile which builds docker image is storing in project's directory
### Heroku
Docker image deploy to Heroku runs automatically using GitHub Actions with push to *andrew-pavlov-socks-api* branch
### Link to the Heroku app:
https://socks-rest-api.herokuapp.com/api/socks
