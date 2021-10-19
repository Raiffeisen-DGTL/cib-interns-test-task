package com.example.socksStoreHouseTestTask.controller;

import com.example.socksStoreHouseTestTask.service.SocksGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksGetController {

    private int count;

    @Autowired
    SocksGetService socksGetService;

    @GetMapping
    public ResponseEntity getSocks(@RequestParam String color, @RequestParam String operation, @RequestParam String cottonPart) {
        try {
            count = socksGetService.getSocks(color, operation, cottonPart);
            return ResponseEntity.ok("запрос выполнен " + count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("параметры запроса отсутствуют или имеют некорректный формат");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("произошла ошибка, не зависящая от вызывающей стороны");
        }
    }

}
