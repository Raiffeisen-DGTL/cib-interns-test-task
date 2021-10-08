package com.example.testtask.store.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socks")
public class SocksEntity {

  @Builder.Default
  private String color = "";

  @Builder.Default
  private Integer cottonPart = 0;

  @Id
  @Builder.Default
  private Integer id = 0;

  @Builder.Default
  private Long quantity = 0l;

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now();


}
