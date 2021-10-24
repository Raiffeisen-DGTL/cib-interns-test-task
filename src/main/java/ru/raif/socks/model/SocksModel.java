package ru.raif.socks.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "socks")
public class SocksModel {

    /**
     * id носков
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Цвет носков
     */
    @Column(name = "color")
    private String color;

    /**
     * Содержание хлопка в процентах
     */
    @Column(name = "cotton_part")
    private int cottonPart;

    /**
     * Количество пар
     */
    @Column(name = "quantity")
    private int quantity;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((color == null) ? 0 : color.hashCode());
        result = prime * result
                + cottonPart;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SocksModel socks = (SocksModel) o;
        return color != null  && Objects.equals(color, socks.color) && Objects.equals(cottonPart, socks.cottonPart);
    }
}
