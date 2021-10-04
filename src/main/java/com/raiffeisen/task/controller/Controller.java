package com.raiffeisen.task.controller;

import com.raiffeisen.task.dto.SockDto;
import com.raiffeisen.task.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final SockService sockService;

    @Autowired
    public Controller(SockService sockService) {
        this.sockService = sockService;
    }

//    POST /api/socks/income
//    Регистрирует приход носков на склад.

    @PostMapping("/socks/income")
    public ResponseEntity<String> incomeSucks(@RequestBody SockDto sockDto){

        return ResponseEntity.status(200).build();
    }
//    POST /api/socks/outcome
//    Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные,
//    но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> outcomeSucks(@RequestBody SockDto sockDto){

        return ResponseEntity.status(200).build();
    }

//    GET /api/socks
//    Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
//    Параметры запроса передаются в URL:
//
//    color — цвет носков, строка;
//    operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;
//    cottonPart — значение процента хлопка в составе носков из сравнения.


}
