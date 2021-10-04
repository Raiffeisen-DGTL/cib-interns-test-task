package com.raiffeisen.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Integer id;

    @Column
    private String color;

    @Column
    @Min(value = 0, message = "Warning! Cotton part should be more than 0%")
    @Max(value = 100, message = "Warning! Cotton part should be less or equal than 100%")
    private Integer cottonPart;

    @Column
    @Min(value = 1, message = "Warning! The quantity cannot be less than 1")
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
