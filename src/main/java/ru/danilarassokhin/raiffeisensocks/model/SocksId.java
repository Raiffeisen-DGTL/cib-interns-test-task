package ru.danilarassokhin.raiffeisensocks.model;

import java.io.Serializable;

/**
 * Represents composite primary key for {@link ru.danilarassokhin.raiffeisensocks.model.Socks}.
 */
public class SocksId implements Serializable {

  private String color;

  private byte cottonPart;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public byte getCottonPart() {
    return cottonPart;
  }

  public void setCottonPart(byte cottonPart) {
    this.cottonPart = cottonPart;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    SocksId socksId = (SocksId) obj;
    return socksId.getColor().equals(this.getColor())
        && socksId.getCottonPart() == this.getCottonPart();
  }
}
