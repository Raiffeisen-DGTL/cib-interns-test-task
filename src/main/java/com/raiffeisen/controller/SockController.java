package com.raiffeisen.controller;

import com.raiffeisen.model.Sock;
import com.raiffeisen.service.SockService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SockController {

    private final SockService service;

    public SockController(SockService service) {
        this.service = service;
    }

    @PostMapping(value = "/api/socks/income", consumes = "application/json", produces = "application/json")
    public void postSocksIncome(@RequestBody Sock sock){
        service.save(sock);
    }

    @PostMapping(value = "/api/socks/outcome", consumes = "application/json", produces = "application/json")
    public void updateSock(@RequestBody Sock sock){
        service.update(sock);
    }

    @RequestMapping(value = "/api/socks", method = RequestMethod.GET)
    public @ResponseBody int getQuantityByCriteria(@RequestParam Map<String, String> queryParameters){
        return service.findByCriteria( new ArrayList<>(queryParameters.values()));
    }

}
