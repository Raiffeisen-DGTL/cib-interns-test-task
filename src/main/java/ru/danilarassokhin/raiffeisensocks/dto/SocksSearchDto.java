package ru.danilarassokhin.raiffeisensocks.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Socks count information.
 */
public class SocksSearchDto {

  @NotBlank(message = "Socks color can't be empty")
  private final String color;

  private final String operation;

  @Min(value = 0, message = "Cotton part must be greater or equals to zero")
  @Max(value = 100, message = "Cotton part can't be greater than 100")
  @NotNull(message = "Socks cotton part is not presented")
  private final Byte cottonPart;

  public SocksSearchDto(@NotBlank(message = "Socks color can't be empty") String color,
                        String operation,
                        @Min(value = 0, message = "Cotton part must be greater or equals to zero")
                        @Max(value = 100, message = "Cotton part can't be greater than 100")
                            Byte cottonPart) {
    this.color = color;
    this.operation = operation;
    this.cottonPart = cottonPart;
  }

  public SocksSearchDto(@NotBlank(message = "Socks color can't be empty")
                            String color) {
    this.color = color;
    this.operation = "";
    this.cottonPart = 0;
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
