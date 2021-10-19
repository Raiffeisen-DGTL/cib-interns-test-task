package ru.lsan.cibinternstesttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lsan.cibinternstesttask.database.service.OutcomeService;
import ru.lsan.cibinternstesttask.dto.OutcomeDto;

@RestController
@RequestMapping("/api/socks/outcome")
public class OutcomeController {

    @Autowired
    private OutcomeService outcomeService;

    @PostMapping
    public ResponseEntity outcome(@RequestBody OutcomeDto outcomeDto) {
        if (outcomeDto.getCottonPart() > 100) {
            return ResponseEntity.status(400).build();
        } else {
            try {
                outcomeService.createOutcome(outcomeDto);
                return ResponseEntity.status(200).build();
            } catch (NullPointerException exception) {
                return ResponseEntity.status(400).build();
            } catch (Exception exception) {
                return ResponseEntity.status(500).build();
            }
        }
    }

}
