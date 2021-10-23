package com.raiffeisen.socks.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String color;
    @Column
    private Integer cottonPart;
    @Column
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return Objects.equals(id, sock.id) &&
                Objects.equals(color, sock.color) &&
                Objects.equals(cottonPart, sock.cottonPart) &&
                Objects.equals(quantity, sock.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart, quantity);
    }
}
