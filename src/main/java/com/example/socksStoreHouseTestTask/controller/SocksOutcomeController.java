package com.example.socksStoreHouseTestTask.controller;

import com.example.socksStoreHouseTestTask.entity.SocksEntity;
import com.example.socksStoreHouseTestTask.service.SocksOutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks/outcome")
public class SocksOutcomeController {

    @Autowired
    SocksOutcomeService socksOutcomeService;

    @PostMapping
    public ResponseEntity outcome(@RequestBody SocksEntity socks) {
        try {
            socksOutcomeService.outcome(socks);
            return ResponseEntity.ok("удалось добавить приход");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("параметры запроса отсутствуют или имеют некорректный формат");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("произошла ошибка, не зависящая от вызывающей стороны");
        }
    }
}