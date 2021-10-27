@ REM Cleans up Docker container and images.

docker container rm sap
docker image rm sap:1-SNAPSHOT
docker image prune --force