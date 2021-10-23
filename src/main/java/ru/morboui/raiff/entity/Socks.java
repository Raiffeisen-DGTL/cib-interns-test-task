package ru.morboui.raiff.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks")
public class Socks {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String color;

  @Column
  private Long cottonPart;

  @Column
  private Long quantity;

  public Socks(Integer id, String color, Long cottonPart, Long quantity) {
    this.id = id;
    this.color = color;
    this.cottonPart = cottonPart;
    this.quantity = quantity;
  }

  public Socks(String color, Long cottonPart, Long quantity) {
    this.color = color;
    this.cottonPart = cottonPart;
    this.quantity = quantity;
  }

  public Socks() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Long getCottonPart() {
    return cottonPart;
  }

  public void setCottonPart(Long cottonPart) {
    this.cottonPart = cottonPart;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }



}
