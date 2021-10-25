# tested on Ubuntu 20
repack-app:
	cd storageService \
	&& mvn clean package -DskipTests \
	&& cp application/target/application-1.0-SNAPSHOT.jar application/src/main/docker \
	&& cd ../

migrate-diff:
	cd storageService/ \
	&& mvn clean install liquibase:diff \
	&& cd ../

migrate-update:
	cd storageService/ \
	&& && mvn liquibase:update \
	&& cd ../

db-clean:
	cd storageService/ \
	&& rm application/src/main/resources/db/changelogs/db.changelog* \
	&& mvn liquibase:dropAll
	cd ../

build:
	docker-compose build backend

dev:
	docker-compose up database backend

first-start:
	make repack-app \
	&& make build \
	&& make migrate-update
