package ru.javabootcamp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javabootcamp.dto.SocksRqDto;
import ru.javabootcamp.service.SocksService;
import ru.javabootcamp.validation.GetRqValidator;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/socks")
public class MainController {

    private final SocksService service;
    private final GetRqValidator getValidator;

    @GetMapping
    public String getStocks(@RequestParam("color") String color,
                            @RequestParam("operation") String operation,
                            @RequestParam("cottonPart") Integer cottonPart) {

        getValidator.validate(color, operation, cottonPart);

        return service.getStocks(color, operation, cottonPart);
    }

    @PostMapping("/income")
    public void addIncome(@Valid @RequestBody SocksRqDto socks) {
        service.addIncome(socks);
    }

    @PostMapping("/outcome")
    public void addOutcome(@Valid @RequestBody SocksRqDto socks) {
        service.addOutcome(socks);
    }

}