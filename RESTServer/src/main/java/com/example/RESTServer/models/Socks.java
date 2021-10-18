package com.example.RESTServer.models;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "Socks")
public class Socks {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String color;
    private int cottonPart;
    private int quantity;

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
