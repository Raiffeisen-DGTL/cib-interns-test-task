package com.example.springsocks.resource;

import com.example.springsocks.domain.Socks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SocksResource.
 *
 * @author Alexander_Tupchin
 */

@RequestMapping(SocksResource.URL)
public interface SocksResource {

    String URL = "/api/socks";

    /**
     * GET /api/socks/{color}{operation}{cottonPart} : Получить общее количество носков с заданными параметрами.
     *
     * @param color Цвет носков (required)
     * @param operation Оператор сравнения значения (required)
     * @param cottonPart Процентное содержание хлопка (required)
     * @return Successful response (status code 200)
     */
    @GetMapping()
    ResponseEntity<Long> getCountSocks(@RequestParam("color") String color,
                                       @RequestParam("operation") String operation,
                                       @RequestParam("cottonPart") Integer cottonPart);

    /**
     * POST /api/socks/income : Добавить носки на склад.
     *
     * @param socks Носки (required)
     * @return Successful response (status code 200)
     */
    @PostMapping(value = "/income")
    ResponseEntity<Void> addSocks(@RequestBody Socks socks);


    /**
     * POST /api/socks/outcome : Изъять носки со склада.
     *
     * @param socks Носки (required)
     * @return Successful response (status code 200)
     */
    @PostMapping(value = "/outcome")
    ResponseEntity<Void> reduceSocks(@RequestBody Socks socks);
}
