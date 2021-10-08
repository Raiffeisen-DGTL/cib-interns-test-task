package com.raiffeizen.demo.controllers;

import com.raiffeizen.demo.models.Socks;
import com.raiffeizen.demo.services.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks/")
public class SocksController {

    @Autowired
    SocksService socksService;

    @GetMapping
    public long findByColorAndCottonPart(@RequestParam("color") String color, @RequestParam("operation") String operation, @RequestParam("cottonPart") int cottonPart){
        return socksService.findByColorAndCottonPart(color, operation, cottonPart);
    }

    @PostMapping(path = "/income")
    @ResponseStatus(HttpStatus.OK)
    public void incomingSocks(@RequestBody Socks socks) {
        socksService.addSocks(socks);
    }

    @PostMapping(path = "/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void outgoingSocks(@RequestBody Socks socks) {
        socksService.removeSocks(socks);
    }

}
