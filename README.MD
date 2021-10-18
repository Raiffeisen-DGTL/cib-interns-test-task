Queries that I tested locally on my machine:

```bash
curl http://localhost:8080/api/socks/all; echo 
curl -v -H "Content-Type: application/json" -d  '{"color":"blue","cottonPart":5,"quantity":5}' http://localhost:8080/api/socks/income; echo 
curl "http://localhost:8080/api/socks?color=blue&operation=moreThan&cottonPart=3"; echo
curl -v -H "Content-Type: application/json" -d  '{"color":"blue","cottonPart":5,"quantity":5}' http://localhost:8080/api/socks/outcome; echo
```

Instruction how to deploy spring boot with docker-compose:
