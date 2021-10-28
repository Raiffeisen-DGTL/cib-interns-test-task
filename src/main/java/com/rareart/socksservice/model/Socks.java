package com.rareart.socksservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "socks")
@AccessType(AccessType.Type.FIELD)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 0, message = "cotton part of the socks must be greater or equals to 0")
    @Max(value = 100, message = "cotton part of the socks must be less or equals to 100")
    @Column(name = "cotton_part", columnDefinition = "TINYINT", nullable = false)
    private byte cottonPart;

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    @Min(value = 0, message = "quantity value of the socks can't be less than 0")
    private int quantity;

    @Valid
    @ManyToOne()
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    public Socks(int id, byte cottonPart, int quantity, Color color) {
        this.id = id;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && quantity == socks.quantity && Objects.equals(color, socks.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cottonPart, quantity, color);
    }
}
