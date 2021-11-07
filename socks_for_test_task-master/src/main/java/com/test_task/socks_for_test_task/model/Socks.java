package com.test_task.socks_for_test_task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "socks")
public class Socks {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String color;

    @Column(name = "cotton_part")
    @Min(0)
    @Max(100)
    private Integer cottonPart;

    @PositiveOrZero
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return id.equals(socks.id) && color.equals(socks.color) && cottonPart.equals(socks.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }
}
