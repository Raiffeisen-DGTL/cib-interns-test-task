package com.example.sock.controller;

import com.example.sock.domain.Socks;
import com.example.sock.enums.Operations;
import com.example.sock.service.ISocksService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SocksController {

    private final ISocksService iSocksService;

    @GetMapping(value = "api/socks")
    public Socks getByColorAndCottonPart(@RequestParam(value = "color") final String color,
                                                   @RequestParam(value = "operation") final Operations operation,
                                                   @RequestParam(value = "cottonPart") final Long cottonPart){
        return iSocksService.getByColorAndCottonPart(color, operation, cottonPart);


    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/income")
    public void addSocks(@RequestBody Socks socks){
        iSocksService.addSocks(socks);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/outcome")
    public void reduceSocks(@RequestBody Socks socks){
        iSocksService.reduceSocks(socks);
    }


}
