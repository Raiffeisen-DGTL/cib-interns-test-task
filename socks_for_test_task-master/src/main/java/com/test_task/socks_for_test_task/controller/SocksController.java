package com.test_task.socks_for_test_task.controller;

import com.test_task.socks_for_test_task.model.Socks;
import com.test_task.socks_for_test_task.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public void income(@Valid @RequestBody Socks socks){
        socksService.income(socks);
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody Socks socks){
        socksService.outcome(socks);
    }

    @GetMapping()
    public int countOfSocks(@RequestParam("color") String color,
                            @RequestParam("operation") String operation,
                            @RequestParam("cottonPart") int cottonPart) {
        return socksService.countOfSocks(color, operation, cottonPart);
    }

}
