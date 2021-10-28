package com.raiffeisensocks.app.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@Table(name = "socks")
public class Socks {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "color", nullable = false)
    private String color;

    @Min(0)
    @Max(100)
    @NotNull
    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;

    @Min(0)
    @NotNull
    @Column(name = "quantity",  nullable = false)
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Socks socks = (Socks) o;
        return id != null && Objects.equals(id, socks.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
