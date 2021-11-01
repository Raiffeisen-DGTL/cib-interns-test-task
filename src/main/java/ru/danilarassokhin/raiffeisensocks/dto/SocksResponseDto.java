package ru.danilarassokhin.raiffeisensocks.dto;

public class SocksResponseDto {

  private final String color;
  private final Byte cottonPart;
  private final Long quantity;

  public SocksResponseDto(String color, Byte cottonPart, Long quantity) {
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
