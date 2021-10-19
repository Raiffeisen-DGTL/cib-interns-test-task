package ru.lsan.cibinternstesttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.cibinternstesttask.database.service.GoodService;
import ru.lsan.cibinternstesttask.dto.GoodDto;

@RestController
@RequestMapping("/api/socks")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping
    public ResponseEntity getSocks(@RequestParam(name = "color") String color, @RequestParam(name = "operation") String operation, @RequestParam(name = "cottonPart") int cottonPart) {
        GoodDto goodDto = new GoodDto(color, operation, cottonPart);
        Long goodsCounts = 0L;
        try {
            goodsCounts = goodService.getGoodsCountBy(goodDto);
            return ResponseEntity.status(200).body(goodsCounts);
        } catch (NullPointerException exception) {
            return ResponseEntity.status(400).build();
        }
    }


}


