package ru.backend.shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.shop.entities.dto.SocksDto;
import ru.backend.shop.service.SocksService;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksRestController {

    @Autowired
    private SocksService socksService;

    @PostMapping(value= "income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocksDto> saveStudent(@RequestBody SocksDto socks) {
        if(isNotValidSocks(socks)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        socksService.addSocks(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocksDto> deleteStudent(@RequestBody SocksDto socks) {
        if(isNotValidSocks(socks)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        socksService.deleteSocks(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SocksDto> getSocks(@RequestParam String color,
                                @RequestParam String operation,
                                @RequestParam Integer cottonPart) {
        return socksService.getSocksByParameters(color, operation,cottonPart);
    }

    private boolean isNotValidSocks(SocksDto socks) {
        int cottonPart = socks.getCottonPart();
        int quantity = socks.getQuantity();

        return cottonPart < 0 || cottonPart > 100 || quantity <= 0;
    }
}