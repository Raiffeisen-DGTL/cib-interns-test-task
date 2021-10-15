package com.oleg.socks.controller;

import com.oleg.socks.dto.SocksDto;
import com.oleg.socks.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/socks")
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final SocksService socksService;

    @Operation(summary = "Метод для регистрации прихода носков на склад")
    @PostMapping(value = "/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addSocks(@RequestBody SocksDto socksDto) {
        socksService.addSocks(socksDto);
    }

    @Operation(summary = "Метод для регистрации отпуска носков со склада")
    @PostMapping(value = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeSocks(@RequestBody SocksDto socksDto) {socksService.removeSocks(socksDto);
    }

    @Operation(summary = "Метод для получения общего количества носков, соответствующих переданным параметрам")
    @GetMapping
    @ResponseBody
    public String getAmount(@RequestParam String color, @RequestParam String operation, @RequestParam String cottonPart) {
         return socksService.getSocks(color, operation, cottonPart).toString();
    }

}
