1. Post - https://socks-raif.herokuapp.com/api/socks/income
2. Post - https://socks-raif.herokuapp.com/api/socks/outcome
3. Get - https://socks-raif.herokuapp.com/api/socks?color=red&operation=moreThan&cottonPart=1

Example 
{
    "color" : "red",
    "cottonPart" : 11,
    "quantity" : 2
}

Run Liquibase 
mvn -Plocal liquibase:update

Added Tests for SocksController