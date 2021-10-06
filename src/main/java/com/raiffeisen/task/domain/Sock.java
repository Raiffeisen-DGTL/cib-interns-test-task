package com.raiffeisen.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity(name = "socks")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "color")
    private String color;

    @Column(name = "cottonpart")
    private Integer cottonPart;

    @Column(name = "quantity")
    private Integer quantity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return Objects.equals(id, sock.id) && Objects.equals(color, sock.color) && Objects.equals(cottonPart, sock.cottonPart) && Objects.equals(quantity, sock.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
