package ru.task.socks.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.task.socks.exception.SocksCustomException;
import ru.task.socks.model.dto.SocksDTO;
import ru.task.socks.model.dto.WarehouseDTO;
import ru.task.socks.service.SocksWarehouseService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "api/socks")
public class SocksWarehouseController {

    private final SocksWarehouseService socksWarehouseService;

    public SocksWarehouseController(SocksWarehouseService socksWarehouseService) {
        this.socksWarehouseService = socksWarehouseService;
    }

    @PostMapping(path = "/income")
    public ResponseEntity<?> socksIncome(@Valid @RequestBody SocksDTO socks) throws SocksCustomException {
        socksWarehouseService.socksIncome(socks);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path = "/outcome")
    public ResponseEntity<?> socksOutcome(@Valid @RequestBody SocksDTO socks) throws SocksCustomException {
        socksWarehouseService.socksOutcome(socks);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<?> param(@Valid WarehouseDTO warehouseDTO) throws SocksCustomException {
        Long quantity = socksWarehouseService.getSocksQuantityByParams(warehouseDTO);
        if (quantity == null) {
            return ResponseEntity.status(HttpStatus.OK).body("0");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(quantity));
        }
    }

}
