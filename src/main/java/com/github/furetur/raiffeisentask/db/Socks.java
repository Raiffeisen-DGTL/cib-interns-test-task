package com.github.furetur.raiffeisentask.db;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@IdClass(SocksId.class)
public class Socks {

  @Id private String color;

  @Id private Integer cottonPart;

  private Integer quantity;

  protected Socks() {}

  public Socks(String color, int cottonPart, int quantity) {
    this.color = color;
    this.cottonPart = cottonPart;
    this.quantity = quantity;
  }

  public String getColor() {
    return color;
  }

  public Integer getCottonPart() {
    return cottonPart;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(int newQuantity) {
    quantity = newQuantity;
  }
}
