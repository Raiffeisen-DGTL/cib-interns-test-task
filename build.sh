./mvnw clean install
cp target/socks-0.0.1-SNAPSHOT.jar src/main/docker/
docker-compose -f src/main/docker/docker-compose.yml up -d
rm src/main/docker/socks-0.0.1-SNAPSHOT.jar