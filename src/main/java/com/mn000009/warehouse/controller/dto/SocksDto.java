package com.mn000009.warehouse.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocksDto {

  private String color;
  private int cottonPart;
  private int quantity;

  public SocksDto(String color, int cottonPart) {
    this.color = color;
    this.cottonPart = cottonPart;
  }

  public SocksDto(String color, int cottonPart, int quantity) {
    this.color = color;
    this.cottonPart = cottonPart;
    this.quantity = quantity;
  }

}
