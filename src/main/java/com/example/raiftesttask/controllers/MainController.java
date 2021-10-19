package com.example.raiftesttask.controllers;

import com.example.raiftesttask.domain.Color;
import com.example.raiftesttask.domain.Sock;
import com.example.raiftesttask.domain.SockDTO;
import com.example.raiftesttask.service.SockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController()
public class MainController {

    @Autowired
    SockService service;

    @PostMapping(value = "api/socks/income")
    @Tag(name = "Добавление носков на склад",description = "возможные цвета: WHITE,BLACK,RED,GREEN,BLUE")
    public ResponseEntity income(@Valid @RequestBody SockDTO dto) {

        service.income(dto);
        return ResponseEntity.status(HttpStatus.valueOf(200)).build();

}

    @DeleteMapping(value = "api/socks/outcome")
    @Tag(name = "Удаление носков со склада",description = "возможные цвета: WHITE,BLACK,RED,GREEN,BLUE")
    public ResponseEntity outcome(@Valid @RequestBody SockDTO dto){
           return service.outcome(dto);
    }

    @GetMapping("/api/socks/search")
    @Tag(name = "Поиск носков на складе",description = "возможные цвета:WHITE,BLACK,RED,GREEN,BLUE")
    public ResponseEntity search(@RequestParam(name = "color") String color,
                                 @RequestParam(name = "operation") String operation,
                                 @RequestParam(name = "cottonPart") Integer cottonPart){
        List<Sock> result = new ArrayList<>();

        switch (operation) {
            case ("moreThan") -> result.addAll(service.findByColorAndCottonPartAfter(Color.valueOf(color.toUpperCase(Locale.ROOT)), cottonPart));
            case ("lessThan") -> result.addAll(service.findByColorAndCottonPartBefore(Color.valueOf(color.toUpperCase()), cottonPart));
            case ("equal") -> result.add(service.findByColorAndCottonPartEquals(Color.valueOf(color.toUpperCase(Locale.ROOT)), cottonPart));
        }
        return new  ResponseEntity(result,HttpStatus.OK);
    }
}
