package ru.raiffeisen.cibinternstesttask.socks;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Пара носков.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "Socks")
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "socks_gen")
    @SequenceGenerator(name = "socks_gen", sequenceName = "socks_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Min(value = 0, message = "Cotton part must be greater or equal 0")
    @Max(value = 100, message = "Cotton part must be less or equal 100")
    @Column(name = "cotton_part", nullable = false)
    private Short cottonPart;

    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "color_name")
    private Color color;

    /**
     * Возвращает носки.
     *
     * @param color цвет
     * @param cottonPart содержание хлопка
     * @param quantity количество на складе
     * @return Socks
     */
    public static Socks of(Color color, Short cottonPart, Integer quantity) {
        var socks = new Socks();
        socks.setColor(color);
        socks.setCottonPart(cottonPart);
        socks.setQuantity(quantity);
        return socks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Socks socks)) {
            return false;
        }
        return Objects.equals(getId(), socks.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
