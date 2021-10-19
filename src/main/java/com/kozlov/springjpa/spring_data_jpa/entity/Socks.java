package com.kozlov.springjpa.spring_data_jpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "socks")
@IdClass(SocksId.class)
public class Socks {
    @Id
    @JsonProperty("color")
    @Column(name="color")
    private String color;

    @Id
    @JsonProperty("cottonPart")
    @Column(name="cotton_part")
    private Integer cottonPart;

    @JsonProperty("quantity")
    @Column(name="quantity")
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && Objects.equals(color, socks.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
