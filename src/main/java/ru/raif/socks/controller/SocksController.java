package ru.raif.socks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.raif.socks.model.SocksModel;
import ru.raif.socks.service.SocksServiceImpl;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksServiceImpl socksService;

    public SocksController(SocksServiceImpl socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public ResponseEntity<String> income(@RequestBody SocksModel socksModel){
        socksService.income(socksModel);
        return ResponseEntity.ok("Носки добавлены");
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> outcome(@RequestBody SocksModel socksModel){
        socksService.outcome(socksModel);
        return ResponseEntity.ok("Носки отпущены");
    }

    @GetMapping
    public int getQuantityOfSocks(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart){
        return socksService.findSocksByParameters(color, operation, cottonPart);
    }
}
