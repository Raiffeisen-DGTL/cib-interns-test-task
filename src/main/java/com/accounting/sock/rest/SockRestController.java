package com.accounting.sock.rest;

import com.accounting.sock.entity.Sock;
import com.accounting.sock.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SockRestController {

    @Autowired
    private SockService sockService;

    // Поставка носков
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/socks/income",
            consumes = "application/json"
    )
    public ResponseEntity<String> socksIncome(@Valid @RequestBody Sock sockIncome) {

        sockService.registerSockIncome(sockIncome);
        return new ResponseEntity<>("Income was successful", HttpStatus.OK);

    }

    // Отгрузка носков
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/socks/outcome",
            consumes = "application/json"
    )
    public ResponseEntity<String> socksOutcome(@Valid @RequestBody Sock sockOutcome) {

        boolean result = sockService.registerSockOutcome(sockOutcome);
        if (result) {
            return new ResponseEntity<>("Outcome was successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not enough socks!", HttpStatus.BAD_REQUEST);
        }
    }

    // Получение кол-ва носков
    @RequestMapping(method = RequestMethod.GET, value = "/api/socks")
    public ResponseEntity<String> getSocksCount(@RequestParam ("color") String color,
                                                @RequestParam ("operation") String operation,
                                                @RequestParam ("cottonPart") Integer cottonPart) {

        Long totalQuantity = sockService.getTotalSockQuantity(color, operation, cottonPart);
        if (totalQuantity == -1) {
            return new ResponseEntity<>("Error: Bad operation!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(totalQuantity.toString(), HttpStatus.OK);

    }

}