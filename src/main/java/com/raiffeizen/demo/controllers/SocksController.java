package com.raiffeizen.demo.controllers;

import com.raiffeizen.demo.models.Socks;
import com.raiffeizen.demo.services.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/socks")
@Validated
public class SocksController {

    @Autowired
    SocksService socksService;

    @GetMapping
    public long findByColorAndCottonPart(@Valid @RequestParam("color") @NotEmpty String color, @Valid @RequestParam("operation") @NotEmpty String operation, @Valid @RequestParam("cottonPart")  @Min(0) @Max(100) int cottonPart){
        return socksService.findByColorAndCottonPart(color, operation, cottonPart);
    }

    @PostMapping(path = "/income")
    @ResponseStatus(HttpStatus.OK)
    public void incomingSocks(@Valid @RequestBody Socks socks) {
        System.out.println(socks.getCottonPart());
        socksService.addSocks(socks);
    }

    @PostMapping(path = "/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void outgoingSocks(@Valid @RequestBody Socks socks) {
        socksService.removeSocks(socks);
    }

}
