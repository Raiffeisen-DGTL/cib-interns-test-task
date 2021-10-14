package com.example.socksmanager.controller;

import com.example.socksmanager.db.socks.service.SocksService;
import com.example.socksmanager.model.dto.SocksBatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks/income")
public class IncomeSocksController {

    @Autowired
    private SocksService socksService;

    @PostMapping
    public ResponseEntity<Object> addSocks(@Valid @RequestBody SocksBatchDto socksBatchDto) {
        try {

            socksService.incomeSocks(socksBatchDto.getColor(), socksBatchDto.getCottonPart(), socksBatchDto.getQuantity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            message.append(((FieldError) error).getField()).append(" - ").append(error.getDefaultMessage()).append("\n");
        });
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultExceptions(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
