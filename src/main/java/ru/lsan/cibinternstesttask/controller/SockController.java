package ru.lsan.cibinternstesttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.cibinternstesttask.database.service.SockService;
import ru.lsan.cibinternstesttask.dto.SockDto;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    private SockService sockService;

    @GetMapping
    public ResponseEntity getSocks(@RequestParam(name = "color") String color, @RequestParam(name = "operation") String operation, @RequestParam(name = "cottonPart") int cottonPart) {
        SockDto sockDto = new SockDto(color, operation, cottonPart);
        Long goodsCounts = 0L;
        try {
            goodsCounts = sockService.getGoodsCountBy(sockDto);
            return ResponseEntity.status(200).body(goodsCounts);
        } catch (NullPointerException exception) {
            return ResponseEntity.status(400).build();
        }
    }

}


