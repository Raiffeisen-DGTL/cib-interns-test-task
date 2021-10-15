package ru.raiffeisen.socksforraif.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.socksforraif.dao.SocksJson;
import ru.raiffeisen.socksforraif.services.SocksService;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    @Autowired
    private SocksService socksService;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity requestWithoutParams(MissingServletRequestParameterException exception){
        return ResponseEntity.badRequest().body("параметры запроса отсутствуют или имеют некорректный формат");
    }

    @GetMapping
    public Integer getSocks(@RequestParam String color, String operation, String cottonPart) {
        return socksService.executeQuery(color, operation, cottonPart);
    }

    @PostMapping("/income")
    public String addSocks(@RequestBody SocksJson socksJson) {
        socksService.socksIncome(socksJson);
        return "the post request was successfully parsed and placed in database";
    }

    @PostMapping("/outcome")
    public String removeSocks(@RequestBody SocksJson socksJson) {
        socksService.socksOutcome(socksJson);
        return "the post request was successfully parsed and placed in database";
    }

}
