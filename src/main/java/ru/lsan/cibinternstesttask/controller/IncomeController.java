package ru.lsan.cibinternstesttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.cibinternstesttask.database.service.IncomeService;
import ru.lsan.cibinternstesttask.dto.IncomeDto;

@RestController
@RequestMapping("/api/socks/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity income(@RequestBody IncomeDto incomeDto) {
        if (incomeDto.getCottonPart() > 100) {
            return ResponseEntity.status(400).build();
        } else {
            try {
                incomeService.createIncome(incomeDto);
                return ResponseEntity.status(200).build();
            } catch (NullPointerException exception) {
                return ResponseEntity.status(400).build();
            } catch (Exception exception) {
                return ResponseEntity.status(500).build();
            }
        }
    }

}
