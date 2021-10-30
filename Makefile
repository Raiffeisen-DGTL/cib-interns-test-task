cb:
	gradlew clean build -x test
doc-b:
	docker build . -t lazarevmatt/socks-api:1.0.0
doc-p:
	docker push lazarevmatt/socks-api:1.0.0
doc-r:
	docker run -it --rm -e DATASOURCE_HOST=192.168.1.31 -p 8080:8080 lazarevmatt/socks-api:1.0.0

build-push:
	gradlew clean build -x test
	docker build . -t lazarevmatt/socks-api:1.0.0
	docker push lazarevmatt/socks-api:1.0.0


