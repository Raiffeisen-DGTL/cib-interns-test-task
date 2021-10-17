package tz.example.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tz.example.project.services.SocksService;

@Controller
public class SocksController {

    @Autowired
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping("/api/socks")
    public int countOfSocks(@RequestParam(name = "color", required = false)String color,
                            @RequestParam(name = "operation", required = false)String operation,
                            @RequestParam(name = "cottonPart", required = false)Integer cottonPart) {
        return socksService.countOfSocks(color, operation, cottonPart);
    }
}
