package alaev.dev.raiffeisentesttask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "socks")
public class Sock {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "color")
  private String color;

  @Column(name = "cotton_part")
  private Byte cottonPart;
}
