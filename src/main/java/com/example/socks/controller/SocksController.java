package com.example.socks.controller;

import com.example.socks.Util.Operations;
import com.example.socks.db.dto.SocksDTO;
import com.example.socks.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Validated
public class SocksController {

    private final SocksService socksService;

    @PostMapping(path = "/socks/income", produces = "application/json")
    public ResponseEntity<?> income(@Valid @RequestBody SocksDTO socks, BindingResult result) {

        try {
            socksService.saveSocks(socks);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("удалось добавить приход");
    }

    @PostMapping(path = "/socks/outcome", produces = "application/json")
    public ResponseEntity<?> outcome(@Valid @RequestBody SocksDTO socksDTO, BindingResult result) {
        try {
            var socks = socksService.getSocks(socksDTO);
            if (socks == null || socks.getQuantity() < socksDTO.getQuantity())
                return new ResponseEntity<>("Параметры запроса отсутствуют или имеют некорректный формат", HttpStatus.BAD_REQUEST);
            socksService.outcome(socks, socksDTO);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("удалось выдать носки");
    }

    @GetMapping(path = "/socks", produces = "application/json")
    public ResponseEntity<String> getSocks(@RequestParam(value = "color") @NotNull @NotBlank String color ,
                                      @RequestParam(value = "operation") Operations operation,
                                      @RequestParam(value = "cottonPart") @Min(0) @Max(100) int cottonPart) {
        try {
            var socksCount = socksService.getSocksByOperation(color, operation, cottonPart);
            return new ResponseEntity<>(String.valueOf(socksCount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
