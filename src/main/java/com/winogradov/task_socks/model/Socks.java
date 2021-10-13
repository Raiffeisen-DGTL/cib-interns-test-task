package com.winogradov.task_socks.model;

import lombok.*;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "socks")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ComponentScan
public class Socks {

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
        Socks socks = (Socks) o;
        return Objects.equals(id, socks.id) && Objects.equals(color, socks.color)
                && Objects.equals(cottonPart, socks.cottonPart) &&
                Objects.equals(quantity, socks.quantity);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}