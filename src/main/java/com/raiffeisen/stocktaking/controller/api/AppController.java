package com.raiffeisen.stocktaking.controller.api;

import com.raiffeisen.stocktaking.dto.SocksModelDTO;
import com.raiffeisen.stocktaking.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class AppController {

    @Autowired
    private AppService service;

    @PostMapping(name = "CreateSocksRecord", value = "/api/socks/income")
    public ResponseEntity<Void> createStudentRecord(@RequestBody @Valid SocksModelDTO request){
        try {
            var saveResult = service.createSocksRecord(request);
            if (saveResult) {
                // 200
                return ResponseEntity.ok().build();
            } else {
                // ошибка валидации
                return ResponseEntity.status(400).build();
            }
        } catch (Exception e) {
            // внутренняя ошибка сервера
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(name = "RemoveSocksRecord", value = "/api/socks/outcome")
    public ResponseEntity removeSocksRecord(@RequestBody @Valid SocksModelDTO request){
        try {
            var saveResult = service.removeSocksRecord(request);
            if (saveResult) {
                // 200
                return ResponseEntity.ok().build();
            } else {
                // ошибка валидации
                return ResponseEntity.status(400).build();
            }
        } catch (Exception e) {
            // внутренняя ошибка сервера
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping(name = "GetByColorAndCottonPart", value = "/api/socks")
    public ResponseEntity<Integer> getByColorAndCottonPart(@RequestParam(value = "color") final String color,
                                                 @RequestParam(value = "operation") final String operation,
                                                  @RequestParam(value = "cottonPart") final int cottonPart){
        try {
            var saveResult = service.findByParams(color, operation, cottonPart);
            if (saveResult != null) {
                // 200
                return ResponseEntity.ok(saveResult);
            } else {
                // ошибка валидации
                return ResponseEntity.status(400).build();
            }
        } catch (Exception e) {
            // внутренняя ошибка сервера
            return ResponseEntity.status(500).build();
        }
    }
}
