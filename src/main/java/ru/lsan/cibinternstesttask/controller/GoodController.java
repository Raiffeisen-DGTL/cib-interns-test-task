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

    @PostMapping("/create")
    public ResponseEntity createItem(@RequestBody GoodDto goodDto) {
        goodService.createGood(goodDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public long getSocks(@RequestBody GoodDto goodDto){
        return goodService.getGoodsCountBy(goodDto);
    }


}
