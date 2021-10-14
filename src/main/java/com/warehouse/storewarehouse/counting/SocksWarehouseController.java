package com.warehouse.storewarehouse.counting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SocksWarehouseController {

    private final SocksService socksService;

    @PostMapping("/income")
    ResponseEntity<SimpleResponse> registerIncome(@Valid  @RequestBody DeliveryBatchSocks deliveryBatchSocks) {
        return ResponseEntity.ok(socksService.registerIncome(deliveryBatchSocks));
    }

    @PostMapping("/outcome")
    ResponseEntity<SimpleResponse> registerOutcome(@Valid @RequestBody DeliveryBatchSocks deliveryBatchSocks) {
        return ResponseEntity.ok(socksService.registerOutcome(deliveryBatchSocks));
    }

    @GetMapping
    ResponseEntity<SocksInfo> getInfo(@RequestParam("color") String color, @RequestParam("operation") String operation, @RequestParam("cottonPart") String cottonPart) {
        return ResponseEntity.ok(socksService.getInfo(color, operation, cottonPart));
    }

    @GetMapping("/ping")
    ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong");
    }
}
