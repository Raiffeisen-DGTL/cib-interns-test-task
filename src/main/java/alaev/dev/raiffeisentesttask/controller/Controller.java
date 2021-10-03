package alaev.dev.raiffeisentesttask.controller;

import alaev.dev.raiffeisentesttask.controller.dto.SockDto;
import alaev.dev.raiffeisentesttask.exception.InvalidCottonPartException;
import alaev.dev.raiffeisentesttask.exception.InvalidOperationException;
import alaev.dev.raiffeisentesttask.exception.InvalidQuantityException;
import alaev.dev.raiffeisentesttask.exception.NotEnoughSocksException;
import alaev.dev.raiffeisentesttask.service.SockService;
import java.util.Objects;
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
    checkCottonPart(sockDto.cottonPart);
    checkQuantity(sockDto.quantity);

    service.addSock(sockDto.color, sockDto.cottonPart, sockDto.quantity);
    return ResponseEntity.status(200).build();
  }

  @PostMapping("/api/socks/outcome")
  public ResponseEntity<String> releaseSocks(@RequestBody SockDto sockDto) {
    checkCottonPart(sockDto.cottonPart);
    checkQuantity(sockDto.quantity);

    service.releaseSocks(sockDto.color, sockDto.cottonPart, sockDto.quantity);
    return ResponseEntity.status(200).build();
  }

  @GetMapping("/api/socks")
  public ResponseEntity<String> getCountSocks(@RequestParam("color") String color,
                                              @RequestParam("operation") String operation,
                                              @RequestParam("cottonPart") Integer cottonPart) {
    checkCottonPart(cottonPart);
    checkOperation(operation);

    return ResponseEntity.ok().body(
        "{\n"
            + "    \"count\" : " + service.getSockByParameters(color, cottonPart, operation) + "\n"
            + "}");
  }

  private void checkOperation(String operation) {
    if (!Objects.equals(operation, "moreThan") && !Objects.equals(operation, "lessThan")
        && !Objects.equals(operation, "equal")) {
      throw new InvalidOperationException(operation);
    }
  }

  private void checkCottonPart(Integer cottonPart) {
    if (cottonPart < 0 || cottonPart > 100) {
      throw new InvalidCottonPartException(String.valueOf(cottonPart));
    }
  }

  private void checkQuantity(Integer quantity) {
    if (quantity <= 0) {
      throw new InvalidQuantityException(String.valueOf(quantity));
    }
  }

  @ExceptionHandler(value = {
      InvalidQuantityException.class,
      InvalidCottonPartException.class,
      NotEnoughSocksException.class,
      InvalidOperationException.class
  })
  public ResponseEntity<String> handleInvalidCottonPartException(
      RuntimeException exception) {
    return ResponseEntity.status(400)
        .body("{\n"
                  + "    \"error\" : \"" + exception.getMessage() + "\"\n"
                  + "}");
  }
}
