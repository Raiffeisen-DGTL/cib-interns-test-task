package ru.morboui.raiff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.morboui.raiff.entity.Socks;
import ru.morboui.raiff.enums.Operations;
import ru.morboui.raiff.service.SocksService;

@RestController
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping(path = "api/socks")
    public Socks getByColorAndCottonPart(
            @RequestParam(value = "color") final String color,
            @RequestParam(value = "operation") final Operations operation,
            @RequestParam(value = "cottonPart") final Long cottonPart) {
        return socksService.getByColorAndCottonPart(color, operation, cottonPart);


    }

    @PostMapping(path = "api/socks/income")
    public void addNewSocks(@RequestBody Socks socks) {
        socksService.addNewSocks(socks);
    }

    @PostMapping(path = "api/socks/outcome")
    public void reduceSocks(@RequestBody Socks socks) {
        socksService.reduceSocks(socks);

    }
}
