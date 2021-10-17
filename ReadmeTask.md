1. Подключение к H2.

    URL: jdbc:h2:tcp://localhost/~/test
    User: sa
    Password: sa


2. Тестирования в Postman.
   
    1) POST ~api/socks/income
    Добавить и обновить по id;
       Пример добавления кол-ва носок, которые уже существуют в базе:
       {
       "id" : 8,
       "quantity": 20
       }
       
       Пример добавления носок:
       {
       "id": 8,
       "color": "WHITE",
       "cottonPart": 56,
       "quantity": 440
       }
       
       Можно добавить носки только определённых цветов:
       RED,
       BLUE,
       GREEN,
       GRAY,
       WHITE,
       BLACK
    
    2) POST ~api/socks/outcome
    Списать по id;
       Пример списания:
       {
       "id" : 8,
       "quantity": 20
       }
       
    3) POST ~api/socks/delete/{id}
    Удалить носки по id;
       
    4) GET ~api/socks/{color}
    Фильтр носок по цвету;