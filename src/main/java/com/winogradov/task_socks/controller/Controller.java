package com.winogradov.task_socks.controller;

import com.winogradov.task_socks.Exception.InvalidCottonPartException;
import com.winogradov.task_socks.Exception.InvalidOperationException;
import com.winogradov.task_socks.Exception.InvalidQuantityException;
import com.winogradov.task_socks.Exception.NotEnoughSocksException;
import com.winogradov.task_socks.dto.SockDto;
import com.winogradov.task_socks.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class Controller {

    private final SocksService service;

    @Autowired
    public Controller(SocksService service) {
        this.service = service;
    }


    // POST /api/socks/income
    // Регистрирует приход носков на склад

    @PostMapping("/api/socks/income")
    public ResponseEntity<String> registerArrivalSocks(@RequestBody SockDto sockDto) {
        checkCottonPart(sockDto.cottonPart);
        checkQuantity(sockDto.quantity);

        service.addSock(sockDto.color, sockDto.cottonPart, sockDto.quantity);
        return ResponseEntity.status(200).build();
    }

    // POST /api/socks/outcome
    // Регистрирует отпуск носков со склада

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<String> releaseSocks(@RequestBody SockDto sockDto) {
        checkCottonPart(sockDto.cottonPart);
        checkQuantity(sockDto.quantity);

        service.releaseSocks(sockDto.color, sockDto.cottonPart, sockDto.quantity);
        return ResponseEntity.status(200).build();
    }


    // GET /api/socks
    // Возвращает общее количество носков на складе

    @GetMapping("/api/socks")
    public ResponseEntity<String> getCountSocks(@RequestParam("color") String color,
                                                @RequestParam("operation") String operation,
                                                @RequestParam("cottonPart") Integer cottonPart) {
        checkCottonPart(cottonPart);
        checkOperation(operation);

        return ResponseEntity.ok().body(
                "{\n"
                        + "    \"count\" : " + service.getSocksByParameters(color, cottonPart, operation) + "\n"
                        + "}");
    }

    private void checkOperation(String operation) {
        if (!Objects.equals(operation, "moreThan") && !Objects.equals(operation, "lessThan")
                && !Objects.equals(operation, "equal")) {
            throw new InvalidOperationException(operation);
        }
    }

    private void checkCottonPart(Integer cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new InvalidCottonPartException(String.valueOf(cottonPart));
        }
    }

    private void checkQuantity(Integer quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException(String.valueOf(quantity));
        }
    }

    @ExceptionHandler(value = {
            InvalidQuantityException.class,
            InvalidCottonPartException.class,
            NotEnoughSocksException.class,
            InvalidOperationException.class
    })
    public ResponseEntity<String> handleInvalidCottonPartException(
            RuntimeException exception) {
        return ResponseEntity.status(400)
                .body("{\n"
                        + "    \"error\" : \"" + exception.getMessage() + "\"\n"
                        + "}");
    }
}