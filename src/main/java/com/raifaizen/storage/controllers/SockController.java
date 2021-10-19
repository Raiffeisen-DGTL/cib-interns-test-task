package com.raifaizen.storage.controllers;

import com.raifaizen.storage.exceptions.StorageException;
import com.raifaizen.storage.models.Sock;
import com.raifaizen.storage.service.SockService;
import com.raifaizen.storage.util.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socks")
public class SockController {

    @Autowired
    private SockService sockService;

    @GetMapping
    public ResponseEntity<List<Sock>> getSocks(
            @RequestParam(required = false, defaultValue = "") String color,
            @RequestParam(required = false) String operation,
            @RequestParam(required = false) Integer cottonPart) {

        List<Sock> socks = sockService.getSocks(color, operation, cottonPart);

        return new ResponseEntity(socks, HttpStatus.OK);
    }

    @PostMapping("/income")
    public ResponseEntity<String> incomeSocks(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity) {

        sockService.income(color, cottonPart, quantity);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> outcomeSocks(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity) {

        sockService.outcome(color, cottonPart, quantity);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 'Exception' чтобы ловить MissingServletRequestParameterException
    @ExceptionHandler(Exception.class)
    private ResponseEntity handleException(Exception e) {
        return RequestHandler.getBadRequest(e);
    }
}
