package ru.raiffeisen.soksapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.soksapp.entity.SearchSockResponse;
import ru.raiffeisen.soksapp.exception.NotEnoughSocsException;
import ru.raiffeisen.soksapp.model.Operation;
import ru.raiffeisen.soksapp.model.Socks;
import ru.raiffeisen.soksapp.service.SockService;

@RestController
public class SocksController {

    private static final Logger log = LoggerFactory.getLogger(SocksController.class);

    @Autowired
    private SockService sockService;


    @PostMapping("/api/socks/income")
    ResponseEntity<?> addNewSock(@RequestBody @Validated Socks sock) {
        log.info("Пришел новый носок: " + sock);
        sockService.addNewSocks(sock);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/socks/outcome")
    ResponseEntity<?> deleteSock(@RequestBody @Validated Socks sock) {
        log.info("Удаляем носок: " + sock);

        try {
            sockService.deleteSocks(sock);
        } catch (NotEnoughSocsException e) {
            log.error("Произошла ошибка при удалении носков", e);
            return new ResponseEntity<>("Количество носков на складе меньше, чем в запросе", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/api/socks", consumes = "*/*")
    ResponseEntity<?> getSocksByCriteria(
            @RequestParam("color") String color,
            @RequestParam("operation") String operation,
            @RequestParam("cottonPart") Integer cottonPart
    ) {
        log.info(String.format("Ищем носок по запросу color: %s operation: %s cottonPart: %d", color, operation, cottonPart));

        Operation enumOperation = Operation.forValue(operation);
        if (enumOperation == null) {
            return new ResponseEntity<>("Неподдержмиваемая операция: " + operation, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new SearchSockResponse(sockService.findSocksByCriteria(color, enumOperation, cottonPart)), HttpStatus.OK);
    }
}
