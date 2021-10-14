package ru.RVS.Raifdemo.controllers;

import org.json.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.RVS.Raifdemo.DAO.SocksDAO;

@RestController
@RequestMapping(value = "/api")
public class SocksController {

    private final SocksDAO sdao;

    public SocksController(SocksDAO sdao) {this.sdao = sdao;}

    /*
    * Регистрирует приход носков на склад.
    * Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:
    * color — цвет носков, строка (например, black, red, yellow);
    * cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
    * quantity — количество пар носков, целое число больше 0.
    *
    * Результаты:
    *
    * HTTP 200 — удалось добавить приход;
    * HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
    * HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).
    * */
    @RequestMapping(value = "/socks/income", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer> income(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        String color, cottonPart, quantity;
        try {
            color = obj.getString("color");
            cottonPart = obj.getString("cottonPart");
            quantity = obj.getString("quantity");
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int result = sdao.income(color, cottonPart, quantity);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (result == -2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    * Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные, но общее количество носков
    * указанного цвета и состава не увеличивается, а уменьшается.
    * */
    @RequestMapping(value = "/socks/outcome", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer>  outcome(@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        String color, cottonPart, quantity;
        try {
            color = obj.getString("color");
            cottonPart = obj.getString("cottonPart");
            quantity = obj.getString("quantity");
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int result = sdao.outcome(color, cottonPart, quantity);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (result == -2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    * Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
    *
    * Параметры запроса передаются в URL:
    *  color — цвет носков, строка;
    *  operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan,
    *    lessThan, equal;
    *  cottonPart — значение процента хлопка в составе носков из сравнения.
    *
    * Результаты:
    *
    * HTTP 200 — запрос выполнен, результат в теле ответа в виде строкового представления целого числа;
    * HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
    * HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).
    * */
    @RequestMapping(value = "/socks/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> socks(@RequestParam("color") String color,
                                         @RequestParam("operation") String operation,
                                         @RequestParam("cottonPart") String cottonPart) {
        int result = sdao.socks(color, operation, cottonPart);
        if (result >= 0) {
            return new ResponseEntity<>(result,HttpStatus.OK);
        } else if (result == -2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
