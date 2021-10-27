@ REM Runs locally a new container.

docker run -p 8080:8080 --name sap -it sap:1-SNAPSHOT

docker container rm sap