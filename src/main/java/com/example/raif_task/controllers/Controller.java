package com.example.raif_task.controllers;

import com.example.raif_task.Dto.SocksDTO;
import com.example.raif_task.entity.Socks;
import com.example.raif_task.services.interfaces.SocksServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/socks")
public class Controller {
    @Autowired
    private SocksServiceInterface socksServiceInterface;

    @PostMapping(value = "/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Socks> addSocks(@RequestBody Socks socks) {
        return socksServiceInterface.save(socks);
    }

    @PostMapping(value = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Socks> removeSocks(@RequestBody Socks socks) {
        return socksServiceInterface.update(socks);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Socks>> getSocks(@RequestParam String color,
                                                @RequestParam String operation,
                                                @RequestParam double cottonPart) {
        return socksServiceInterface.get(new SocksDTO(color, operation, cottonPart));
    }
}
