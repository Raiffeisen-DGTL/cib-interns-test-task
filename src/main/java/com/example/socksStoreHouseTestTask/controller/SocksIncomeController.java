package com.example.socksStoreHouseTestTask.controller;

import com.example.socksStoreHouseTestTask.entity.SocksEntity;
import com.example.socksStoreHouseTestTask.service.SocksIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/socks/income")
public class SocksIncomeController {

    @Autowired
    SocksIncomeService socksIncomeService;

    @PostMapping
    public ResponseEntity income(@RequestBody SocksEntity socks) {
        try {
            socksIncomeService.income(socks);
            return ResponseEntity.ok("удалось добавить приход");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("параметры запроса отсутствуют или имеют некорректный формат");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("произошла ошибка, не зависящая от вызывающей стороны");
        }
    }
}
