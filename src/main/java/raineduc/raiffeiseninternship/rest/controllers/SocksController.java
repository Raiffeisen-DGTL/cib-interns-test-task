package raineduc.raiffeiseninternship.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raineduc.raiffeiseninternship.services.WarehouseService;
import raineduc.raiffeiseninternship.services.dto.SocksBatch;
import raineduc.raiffeiseninternship.services.dto.SocksRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
@Validated
public class SocksController {
    private WarehouseService warehouseService;

    @Autowired
    public SocksController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public ResponseEntity<String> getSocks(@Valid SocksRequest socksRequest) {
        String result = String.valueOf(warehouseService.getSocksCount(socksRequest));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/income")
    public ResponseEntity<Void> registerIncome(@Valid @RequestBody SocksBatch socksBatch) {
        warehouseService.registerSocksIncome(socksBatch);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<Void> registerOutcome(@Valid @RequestBody SocksBatch socksBatch) {
        warehouseService.registerSocksOutcome(socksBatch);
        return ResponseEntity.ok().build();
    }
}
