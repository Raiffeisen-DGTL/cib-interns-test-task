package com.task.raif.controller;
import com.task.raif.dto.SocksDto;
import com.task.raif.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Map;

@RestController
@Validated
@Tag(name = "Контроллер носков", description = "Позволяет находить, добавлять и забирать носки со склада")
public class SocksController {
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/api/socks/income")
    @Operation(
            summary = "Добавление носков",
            description = "Позволяет зарегестрировать прибытие носков на склад"
    )
    public ResponseEntity<?> socksIncome(@Valid @RequestBody SocksDto socksDTO) {
        socksService.income(socksDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/socks/outcome")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удаление носков",
            description = "Позволяет зарегестрировать убытие носков со склада"
    )
    public void socksOutcome(@Valid @RequestBody SocksDto socksDTO) {
        socksService.outcome(socksDTO);
    }

    @GetMapping("/api/socks")
    @Operation(
            summary = "Найти носки",
            description = "Позволяет найти нужную пару носков на складе"
    )
    public ResponseEntity<?> getSocksController(
            @RequestParam @NotBlank @Parameter(description = "Цвет носков") String color,
            @RequestParam @Pattern(regexp = "moreThan|lessThan|equal") @Parameter(description = "Название операции поиска") String operation,
            @RequestParam @Min(0) @Max(100) @Parameter(description = "Содержание хлопка") int cottonPart) {
        int quantity = socksService.getSocksByParams(color, operation, cottonPart);
        return new ResponseEntity<>(Map.of("Quantity", quantity), HttpStatus.OK);
    }
}
