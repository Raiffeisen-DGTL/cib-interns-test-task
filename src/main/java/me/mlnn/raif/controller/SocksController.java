package me.mlnn.raif.controller;

import lombok.RequiredArgsConstructor;
import me.mlnn.raif.model.SocksModel;
import me.mlnn.raif.service.SocksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService srv;
    
    @PostMapping(value = "/income")
    public void addSocks(@RequestBody SocksModel socks) {
        srv.addSocks(socks);
    }
    
    @PostMapping(value = "/outcome")
    public void removeSocks(@RequestBody SocksModel socks) {
        srv.removeSocks(socks);
    }
    
    @GetMapping(value = "/")
    public Integer getNumberOfSocks(@RequestParam String color,
                                    @RequestParam String operation,
                                    @RequestParam Integer cottonPart) {
        return srv.getNumberOfSocks(color, operation, cottonPart);
    }
}
