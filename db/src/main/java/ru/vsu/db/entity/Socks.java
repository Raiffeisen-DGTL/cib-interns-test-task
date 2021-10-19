package ru.vsu.db.entity;


import javax.persistence.*;

@Entity(name = "socks")
@Table(schema = "socks_storage", name = "socks")
public class Socks {

  @EmbeddedId
  private SocksId id;

  @Column(name = "quantity")
  private Integer quantity;


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public SocksId getId() {
    return id;
  }

  public void setId(SocksId id) {
    this.id = id;
  }
}
