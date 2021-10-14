package ru.raiffeisen.dgtl.cib.intern.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.dgtl.cib.intern.task.entity.Socks;
import ru.raiffeisen.dgtl.cib.intern.task.entity.SocksId;
import ru.raiffeisen.dgtl.cib.intern.task.service.SocksServiceImpl;

import javax.validation.Valid;

@RestController
public class SocksController {

    private final SocksServiceImpl socksService;

    @Autowired
    public SocksController(SocksServiceImpl socksService) {
        this.socksService = socksService;
    }

    @GetMapping("/api/socks")
    Long quantitySocks(@RequestParam("operation") Operation operation,
                       @RequestParam("color") String color, @RequestParam("cottonPart") Byte cottonPart) {
        return socksService.quantity(new SocksId(color, cottonPart), operation);
    }

    @PostMapping("/api/socks/income")
    public ResponseEntity<String> incomeSocks(@Valid @RequestBody Socks socks) {
        socksService.income(socks);
        return ResponseEntity.ok("Socks added!");
    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<String> outcomeSocks(@Valid @RequestBody Socks socks) {
        socksService.outcome(socks);
        return ResponseEntity.ok("Socks removed!");
    }
}
