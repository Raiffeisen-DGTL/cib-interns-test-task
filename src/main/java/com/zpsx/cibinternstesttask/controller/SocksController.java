package com.zpsx.cibinternstesttask.controller;

import com.zpsx.cibinternstesttask.domain.Operation;
import com.zpsx.cibinternstesttask.domain.SockStock;
import com.zpsx.cibinternstesttask.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/socks")
public class SocksController {

    @Autowired SocksService socksService;

    @GetMapping
    public String getSocksQuantity(@RequestParam String color,
                                   @RequestParam Operation operation,
                                   @RequestParam Byte cottonPart){
        return socksService.getSocksQuantity(color, operation, cottonPart).toString();
    }

    @PostMapping(path="income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addSocks(@RequestBody @Valid SockStock income) {
        socksService.addSocks(income);
    }

    @PostMapping(path = "outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdrawSocks(@RequestBody @Valid SockStock withdraw){
        socksService.withdrawSocks(withdraw);
    }
}
