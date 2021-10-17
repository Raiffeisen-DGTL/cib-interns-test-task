package com.github.furetur.raiffeisentask.controller;

import com.github.furetur.raiffeisentask.restData.OperationType;
import com.github.furetur.raiffeisentask.restData.SocksJson;
import com.github.furetur.raiffeisentask.service.SocksService;
import com.github.furetur.raiffeisentask.service.SocksServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocksController {

  private final SocksService socksService;

  public SocksController(SocksServiceImpl socksService) {
    this.socksService = socksService;
  }

  @GetMapping("/socks")
  public int countSocks(
      @RequestParam(name = "color") String color,
      @RequestParam(name = "operation") OperationType operation,
      @RequestParam(name = "cottonPart") int cottonPart) {
    return socksService.countSocks(color, operation, cottonPart);
  }

  @PostMapping(value = "/socks/income", consumes = "application/json")
  public void incomeSocks(@RequestBody SocksJson socks) {
    socksService.addSocks(socks.color(), socks.cottonPart(), socks.quantity());
  }

  @PostMapping(value = "/socks/outcome", consumes = "application/json")
  public void outcomeSocks(@RequestBody SocksJson socks) {
    socksService.removeSocks(socks.color(), socks.cottonPart(), socks.quantity());
  }
}
