package com.mn000009.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "colors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Color {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title")
  @NotBlank
  private String title;


  public Color(String title) {
    this.title = title;
  }

}
