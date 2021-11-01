package ru.danilarassokhin.raiffeisensocks.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiParam;

public class SocksServiceOutcomeDto {

  @ApiParam(name = "color", example = "yellow", required = true)
  @NotBlank(message = "Socks color can't be null")
  private final String color;

  @ApiParam(name = "cottonPart", example = "10", required = true, type = "byte")
  @Min(value = 0, message = "Cotton part must be greater or equal to zero")
  @Max(value = 100, message = "Cotton part must be less or equal to zero")
  private final Byte cottonPart;

  @ApiParam(name = "quantity", example = "11", required = true, type = "long")
  @Min(value = 1, message = "Socks quantity must be greater than zero")
  private final Long quantity;

  public SocksServiceOutcomeDto(@NotBlank(message = "Socks color can't be null")
                             String color,
                         @Min(value = 0, message = "Cotton part must be greater or equal to zero")
                         @Max(value = 100, message = "Cotton part must be less or equal to zero")
                             Byte cottonPart,
                         @Min(value = 1, message = "Socks quantity must be greater than zero")
                             Long quantity) {
    this.color = color;
    this.cottonPart = cottonPart;
    this.quantity = quantity;
  }

  public String getColor() {
    return color;
  }

  public Byte getCottonPart() {
    return cottonPart;
  }

  public Long getQuantity() {
    return quantity;
  }

}
