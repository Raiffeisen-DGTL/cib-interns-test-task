Для запуска
git clone https://github.com/Winogradov/task_socks.git

cd task_socks/

docker-compose up -d

Тест API
POST: http://83.161.76.203:9000/api/socks/income

POST: http://83.161.76.203:9000api/socks/outcome

GET: http://83.161.76.203:9000/api/socks?color=yellow&operation=lessThan&cottonPart=15
