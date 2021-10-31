package com.example.socksFinal.controllers;

import com.example.socksFinal.model.SocksApp;
import com.example.socksFinal.services.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socks")

public class SocksController {
    SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    public ResponseEntity<List<SocksApp>> getAllSocks() {
        List<SocksApp> socks = socksService.getSocks();
        return new ResponseEntity<>(socks, HttpStatus.OK);
    }

    @PostMapping("/income")
    public ResponseEntity<SocksApp> saveSocks(@RequestBody List<SocksApp> socks) {
        for (SocksApp sock : socks) {
            if (sock.getCottonPart() < 0 || sock.getCottonPart() > 100) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        socksService.insert(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<SocksApp> deleteSocks(@RequestBody List<SocksApp> socks) {
        for (SocksApp sock : socks) {
            if (sock.getCottonPart() < 0 || sock.getCottonPart() > 100) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        boolean deleteResult = socksService.deleteSocks(socks);
        if (deleteResult) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<SocksApp> countSocks (@RequestParam(name = "color") String color,
                                                @RequestParam(name = "cottonPart") int cottonPart,
                                                @RequestParam(name = "operation") String operation) {
        switch (operation) {
            case "lessThan":
                socksService.countSocksWithCottonLess(color, cottonPart);
                break;

            case "moreThan":
                socksService.countSocksWithCottonMore(color, cottonPart);
                break;

            case "equal":
                socksService.countSocksBy(color, cottonPart);
                break;

            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}

