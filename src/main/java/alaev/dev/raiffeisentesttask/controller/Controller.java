package alaev.dev.raiffeisentesttask.controller;

import alaev.dev.raiffeisentesttask.controller.dto.SockDto;
import alaev.dev.raiffeisentesttask.exception.InvalidCottonPartException;
import alaev.dev.raiffeisentesttask.exception.InvalidQuantityException;
import alaev.dev.raiffeisentesttask.exception.NotEnoughSocksException;
import alaev.dev.raiffeisentesttask.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final SockService service;

  @Autowired
  public Controller(SockService service) {
    this.service = service;
  }

  @PostMapping("/api/socks/income")
  public ResponseEntity<String> registerArrivalSocks(@RequestBody SockDto sockDto) {
    checkingInput(sockDto.cottonPart, sockDto.quantity);

    service.addSock(sockDto.color, sockDto.cottonPart, sockDto.quantity);
    return ResponseEntity.status(200).build();
  }

  @PostMapping
  public ResponseEntity<String> releaseSocks(@RequestBody SockDto sockDto) {
    checkingInput(sockDto.cottonPart, sockDto.quantity);

    service.releaseSocks(sockDto.color, sockDto.cottonPart, sockDto.quantity);
    return ResponseEntity.status(200).build();
  }

  @GetMapping
  public ResponseEntity<String> getAllSocks(@RequestParam("color") String color,
                                            @RequestParam("cottonPart") Integer cottonPart,
                                            @RequestParam("quantity") Integer quantity) {
    return ResponseEntity.ok().build();
  }

  private void checkingInput(Integer cottonPart, Integer quantity) {
    if (cottonPart < 0 || cottonPart > 100) {
      throw new InvalidCottonPartException(String.valueOf(cottonPart));
    }

    if (quantity <= 0) {
      throw new InvalidQuantityException(String.valueOf(quantity));
    }
  }

  @ExceptionHandler(value = {
      InvalidQuantityException.class,
      InvalidCottonPartException.class,
      NotEnoughSocksException.class
  })
  public ResponseEntity<String> handleInvalidCottonPartException(
      RuntimeException exception) {
    return ResponseEntity.status(400)
        .body("{\n"
                  + "    \"error\" : \"" + exception.getMessage() + "\"\n"
                  + "}");
  }
}
