package com.rareart.socksservice.model;

import lombok.*;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "color")
@AccessType(AccessType.Type.FIELD)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "color name field cannot be empty")
    @Column(nullable = false, unique = true)
    private String name;

    public Color(int id, String color) {
        this.id = id;
        this.name = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return Objects.equals(name, color1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
