package ru.morboui.raiff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.morboui.raiff.entity.Socks;
import ru.morboui.raiff.service.SocksService;

@RestController
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
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
