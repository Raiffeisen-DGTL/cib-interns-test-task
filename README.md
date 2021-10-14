# socks-api  
  path: \   
application.prop = src/main/resources  
schema.sql   
server.port=1000  
spring.datasource.username= postgres  
spring.datasource.password= postgres  
  
  
# comments  
База данных: PostgreSQL - поднимается в докер-контейнере   
Создается пустой - schema.sql проодублирован в корневом каталоге

docker-compose up  
mvn clean compile   
mvn spring-boot:run  
