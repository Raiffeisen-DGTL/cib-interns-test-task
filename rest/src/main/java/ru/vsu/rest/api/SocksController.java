package ru.vsu.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.vsu.service.logic.SocksService;
import ru.vsu.service.model.SocksDto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/socks")
@Validated
public class SocksController {

  private final SocksService service;

  @Autowired
  public SocksController(SocksService service) {
    this.service = service;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Integer findByParams(
    @RequestParam @NotBlank(message = "color has not to be blank")
    @Pattern(regexp = "[a-z]+", message = "color can't contain anything but letters") String color,
    @RequestParam @NotBlank(message = "operation has not to be blank")
    @Pattern(regexp = "(equal)$|(lessThan)$|(moreThan)$", message = "invalid operation") String operation,
    @RequestParam
    @Min(value = 0, message = "cottonPart has not to be negative")
    @Max(value = 100, message = "cottonPart has to be less than 101") Integer cottonPart) {
    return service.findQuantityByParams(color, operation, cottonPart);
  }

  @PostMapping(path = "/income", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public SocksDto addSocks(@RequestBody @Valid SocksDto dto) {
    return service.addSocks(dto);
  }

  @PostMapping(path = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Validated(SocksDto.OnOutcome.class)
  public SocksDto removeSocks(@RequestBody @Valid SocksDto dto) {
    return service.removeSocks(dto);
  }

}
