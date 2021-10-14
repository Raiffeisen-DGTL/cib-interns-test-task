package com.example.company.controller;

import com.example.company.entity.Operation;
import com.example.company.entity.Sock;
import com.example.company.service.SockService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/")
public class SockController {


    private final SockService service;

    public SockController(SockService service) {
        this.service = service;
    }

    @GetMapping("/socks")
    private Sock getByColorAndCottonPart(@RequestParam(value = "color") String color,
                                         @RequestParam(value = "operation")Operation operation,
                                         @RequestParam(value = "cottonPart") int cottonPart){
        return service.getSockByColorAndCottonPart(color, operation, cottonPart);
    }

    @PostMapping("/socks/income")
    @ResponseStatus(OK)
    public void addSock(@RequestBody Sock sock){
        service.addSocks(sock);
    }

    @PostMapping("/socks/outcome")
    @ResponseStatus(OK)
    public void reduceSock(@RequestBody Sock sock){
        service.reduceSocks(sock);
    }
}
