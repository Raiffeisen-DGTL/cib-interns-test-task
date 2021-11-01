package ru.danilarassokhin.raiffeisensocks.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SocksServiceSearchDto {

  @NotBlank(message = "Socks color can't be empty")
  private final String color;

  private final String operation;

  @Min(value = 0, message = "Cotton part must be greater or equals to zero")
  @Max(value = 100, message = "Cotton part can't be greater than 100")
  private final Byte cottonPart;

  public SocksServiceSearchDto(@NotBlank(message = "Socks color can't be empty") String color,
                        String operation,
                        @Min(value = 0, message = "Cotton part must be greater or equals to zero")
                        @Max(value = 100, message = "Cotton part can't be greater than 100")
                            Byte cottonPart) {
    this.color = color;
    this.operation = operation;
    this.cottonPart = cottonPart;
  }

  public String getColor() {
    return color;
  }

  public String getOperation() {
    return operation;
  }

  public Byte getCottonPart() {
    return cottonPart;
  }

}
