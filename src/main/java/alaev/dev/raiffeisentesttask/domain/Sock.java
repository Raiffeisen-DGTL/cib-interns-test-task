package alaev.dev.raiffeisentesttask.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "socks")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "color")
  private String color;

  @Column(name = "cotton_part")
  private Integer cottonPart;

  @Column(name = "quantity")
  private Integer quantity;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sock sock = (Sock) o;
    return Objects.equals(id, sock.id) && Objects.equals(color, sock.color)
        && Objects.equals(cottonPart, sock.cottonPart) && Objects.equals(quantity,
                                                                         sock.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
