package com.test_task.socks_for_test_task.controller;

import com.test_task.socks_for_test_task.model.Socks;
import com.test_task.socks_for_test_task.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/income")
    public void income(@RequestBody Socks socks){
        socksService.income(socks);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/outcome")
    public void outcome(@RequestBody Socks socks){
        socksService.outcome(socks);
    }

    @GetMapping()
    public int countOfSocks(@RequestParam("color")String color,
                            @RequestParam("operation")String operation,
                            @RequestParam("cottonPart")int cottonPart) {
        return socksService.countOfSocks(color, operation, cottonPart);
    }

}
