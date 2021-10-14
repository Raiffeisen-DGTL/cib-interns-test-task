package com.mn000009.warehouse.controller;

import com.mn000009.warehouse.controller.dto.SocksDto;
import com.mn000009.warehouse.domain.Operation;
import com.mn000009.warehouse.domain.SocksPackage;
import com.mn000009.warehouse.exception.IllegalColorException;
import com.mn000009.warehouse.exception.IllegalCottonPartException;
import com.mn000009.warehouse.exception.IllegalQuantityException;
import com.mn000009.warehouse.exception.IncorrectOperationException;
import com.mn000009.warehouse.service.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SocksController {

  private final SocksService socksService;

  public SocksController(SocksService socksService) {
    this.socksService = socksService;
  }

  @PostMapping("/socks/income")
  public ResponseEntity<SocksPackage> doIncome(@RequestBody SocksDto socks) {
    checkColor(socks.getColor());
    checkCottonPart(socks.getCottonPart());
    checkQuantity(socks.getQuantity());
    socksService.income(socks);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/socks/outcome")
  public ResponseEntity<SocksPackage> doOutcome(@RequestBody SocksDto socks) {
    checkColor(socks.getColor());
    checkCottonPart(socks.getCottonPart());
    checkQuantity(socks.getQuantity());
    socksService.outcome(socks);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/socks")
  public ResponseEntity<String> doStatus(@RequestParam String color, @RequestParam String operation,
                                         @RequestParam int cottonPart) {
    checkColor(color);
    checkCottonPart(cottonPart);
    try {
      return new ResponseEntity<>(socksService.getStatus(color, Operation.valueOf(operation.toUpperCase()),
          cottonPart), HttpStatus.OK);
    } catch (IllegalArgumentException exception) {
      throw new IncorrectOperationException(operation);
    }
  }

  private void checkColor(String color) {
    if (color == null || color.isBlank()) {
      throw new IllegalColorException(color);
    }
  }

  private void checkCottonPart(int cottonPart) {
    if (cottonPart < 0 || cottonPart > 100) {
      throw new IllegalCottonPartException(String.valueOf(cottonPart));
    }
  }

  private void checkQuantity(int quantity) {
    if (quantity < 1) {
      throw new IllegalQuantityException(String.valueOf(quantity));
    }
  }

}
