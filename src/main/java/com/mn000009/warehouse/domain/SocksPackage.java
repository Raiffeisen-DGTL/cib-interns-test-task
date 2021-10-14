package com.mn000009.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "socks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class SocksPackage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @NotNull
  private Color color;

  @Column(name = "cotton_Part")
  @Range(min = 0, max = 100)
  private int cottonPart;

  @Column(name = "quantity")
  @Min(0)
  private int quantity;

  public SocksPackage(Color color, int cottonPart, int quantity) {
    this.color = color;
    this.cottonPart = cottonPart;
    this.quantity = quantity;
  }

}