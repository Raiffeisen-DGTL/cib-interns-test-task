package ru.khromov.raiftask.DAO;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String color;

    @Column(nullable = false)
    Integer cottonPart;

    @Column(nullable = false)
    Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sock socks = (Sock) o;
        return id != null && Objects.equals(id, socks.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }


}