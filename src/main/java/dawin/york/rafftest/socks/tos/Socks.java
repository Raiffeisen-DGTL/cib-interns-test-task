package dawin.york.rafftest.socks.tos;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SOCKS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@IdClass(SocksId.class)
public class Socks {
    @Id
    @Column(name = "color")
    private String color;

    @Id
    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "quantity")
    private int quantity;

    public boolean isValid() {
        return !(cottonPart < 0 || cottonPart > 100 || quantity <= 0 || color == null || color.isBlank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Socks socks = (Socks) o;
        return color != null && Objects.equals(color, socks.color)
                && cottonPart != null && Objects.equals(cottonPart, socks.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
