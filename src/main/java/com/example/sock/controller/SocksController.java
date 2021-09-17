package com.example.sock.controller;

import com.example.sock.domain.Socks;
import com.example.sock.service.ISocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SocksController {
    @Autowired
    private ISocksService iSocksService;

    @GetMapping(value = "api/socks")
    public Optional<Socks> getByColorAndCottonPart(@RequestParam(value = "color") final String color,
                                                   @RequestParam(value = "operation", defaultValue = "moreThan") final String operation,
                                                   @RequestParam(value = "cottonPart", defaultValue = "0") final int cottonPart){
        return iSocksService.getByColorAndCottonPart(color, operation, cottonPart);


    }
    @PostMapping(value = "api/socks/income")
    public ResponseEntity addSocks(@RequestBody Socks socks){
        iSocksService.addSocks(socks);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "api/socks/outcome")
    public ResponseEntity reduceSocks(@RequestBody Socks socks){
        iSocksService.reduceSocks(socks);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
