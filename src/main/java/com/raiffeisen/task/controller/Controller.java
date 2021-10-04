package com.raiffeisen.task.controller;

import com.raiffeisen.task.dto.SockDto;
import com.raiffeisen.task.service.SockService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@Log
@Validated
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
    public ResponseEntity<String> incomeSocks(@RequestBody SockDto sockDto){
        log.info("Income " + sockDto);
        sockService.addSocks(sockDto.getColor(), sockDto.getCottonPart(), sockDto.getQuantity());
        return ResponseEntity.ok("Successful addition of socks!");
    }
//    POST /api/socks/outcome
//    Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные,
//    но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> outcomeSocks(@RequestBody SockDto sockDto){
        log.info("Outcome " + sockDto);
        sockService.removeSocks(sockDto.getColor(), sockDto.getCottonPart(), sockDto.getQuantity());
        return ResponseEntity.ok("Successful removal of socks!");
    }

//    GET /api/socks
//    Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
//    Параметры запроса передаются в URL:
//
//    color — цвет носков, строка;
//    operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;
//    cottonPart — значение процента хлопка в составе носков из сравнения.
    @GetMapping("/socks")
    public ResponseEntity<String> returnTotalNumberSocks (@RequestParam("color") String color,
                                                          @RequestParam("operation") String operation,
                                                          @RequestParam("cottonPart") @Min(0) @Max(100) Integer cottonPart) {
        log.info("Get operation: " + color + " : " + operation + " : " + cottonPart);
        sockService.getTotalSocksByParam(color,cottonPart, operation);
        return ResponseEntity.ok().body("{\n" +
                "\"quantity\" : " +
                "\n}");
    }

}
