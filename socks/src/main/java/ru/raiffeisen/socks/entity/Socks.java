package ru.raiffeisen.socks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(0)
    @Max(100)
    @NotNull
    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Min(0)
    @NotNull
    @Column(name = "quantity")
    private Long quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    public Socks(Integer cottonPart, Long quantity, Color color) {
        this.cottonPart = cottonPart;
        this.quantity = quantity;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return id.equals(socks.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                ", color=" + color +
                '}';
    }
}
