package com.home.sock.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "socks")
public class Sock implements Serializable {

  @Column(nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sock_generator")
  @SequenceGenerator(name = "sock_generator", sequenceName = "sock_seq", allocationSize = 1)
  private long id;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "colors_id")
  private Color color;


  @Column(name = "quantity")
  private int quantity;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "composites_id")
  private Composite composite;


  public Sock() {
  }


  public Sock(Color color, int quantity, Composite composite) {
    this.color = color;
    this.quantity = quantity;
    this.composite = composite;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Composite getComposite() {
    return composite;
  }

  public void setComposite(Composite composite) {
    this.composite = composite;
  }



  public void incomeSocks(int quantity) {
    this.quantity += quantity;
  }

  public void outcomeSocks(int quantity) {
    this.quantity -= quantity;
  }

}
