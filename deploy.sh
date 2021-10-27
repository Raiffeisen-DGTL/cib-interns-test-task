mvn clean install -DskipTests
docker build . --tag storage
docker-compose -f docker-compose.yml up -d