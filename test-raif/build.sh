set -e
cwd=$(pwd)

echo "Start building"

# Build storage and docker
echo ">>>> Storage building..."
cd storage
mvn clean package -DskipTests=true -P compose
echo ">>>> Storage docker image building..."
docker build -t test_raif_storage .
echo ">>>> Storage completed!"
cd $cwd