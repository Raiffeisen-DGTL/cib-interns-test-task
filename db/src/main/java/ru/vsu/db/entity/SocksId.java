package ru.vsu.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SocksId implements Serializable {

  @Column(name = "color")
  private String color;

  @Column(name = "cotton_part")
  private Integer cottonPart;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SocksId socksId = (SocksId) o;
    return Objects.equals(color, socksId.color) && Objects.equals(cottonPart, socksId.cottonPart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, cottonPart);
  }
}
