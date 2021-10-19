package ru.vsu.service.model;

import ru.vsu.service.validation.IsAvailableQuantity;
import ru.vsu.service.validation.SocksPairExist;

import javax.validation.constraints.*;

@SocksPairExist(groups = {SocksDto.OnOutcome.class})
@IsAvailableQuantity(groups = {SocksDto.OnOutcome.class})
public class SocksDto {

  public interface OnOutcome {
  }

  @NotBlank(message = "color can't be blank")
  @Pattern(regexp = "[a-z]+", message = "color can't contain anything but letters")
  private String color;

  @NotNull(message = "cottonPart can't be null")
  @Min(value = 0, message = "cottonPart can't be negative")
  @Max(value = 100, message = "cottonPart can't be more than 100")
  private Integer cottonPart;

  @NotNull(message = "quantity can't be null")
  @Min(value = 1, message = "quantity has to be positive")
  private Integer quantity;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Integer getCottonPart() {
    return cottonPart;
  }

  public void setCottonPart(Integer cottonPart) {
    this.cottonPart = cottonPart;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
