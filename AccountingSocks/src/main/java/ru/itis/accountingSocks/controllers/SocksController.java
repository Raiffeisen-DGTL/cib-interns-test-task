package ru.itis.accountingSocks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itis.accountingSocks.dto.SocksDto;
import ru.itis.accountingSocks.forms.SocksForm;
import ru.itis.accountingSocks.services.SocksService;

@RestController
public class SocksController {

    @Autowired
    private SocksService socksService;

    @PostMapping("api/socks/income")
    public SocksDto addSocks(@RequestBody SocksForm socks) {
        return socksService.addSocks(socks);
    }

    @PostMapping("api/socks/outcome")
    public SocksDto reduceSocks(@RequestBody SocksForm socks) {
        return socksService.reduceSocks(socks);
    }

    @GetMapping("api/socks")
    public int getTotalNumberSocks(@RequestParam("color") String color,
                                   @RequestParam("operation") String operation,
                                   @RequestParam("cottonPart") int cottonPart) {
        return socksService.getTotalQuantitySocks(color, operation, cottonPart);
    }

}
