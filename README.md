# Test task for Raiffeisen Bank (ShopWarehouse)

### To that project work correctly you should follow next steps:
* Create database with name "shop_warehouse" in postgresql;
* Change database login and password in application.properties
  * These lines:
  
    **spring.datasource.username=YOUR_LOGIN**
  
    **spring.datasource.password=YOUR_PASSWORD**

* Run "ShopWarehouse" project. Please, check if tables in the database have been created;
* Open browser and insert "http://localhost:8080/swagger-ui/index.html" in search field; 
* Add "/v3/api-docs" in swagger search field
* Authorize through "Authorize" button as "Maxim.Isaev" and "12332112"
* Work with "SocksController"

Also, if you have necessity, you can use RoleController and UserController.

Please feel free to contact me if you need any further information.