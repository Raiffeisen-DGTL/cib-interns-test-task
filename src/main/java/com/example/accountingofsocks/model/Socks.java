package com.example.accountingofsocks.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@DynamicUpdate(value = true) // По умолчанию Hibernate если мы обновляем только одно поле.
// То он все равно обновляет все поля. Он делает это, потому что с нами параллельно может кто то работать с этим обьектом,
// и мы могли получить не то что хотели. По этому Hibernate дает нам возможность вк флажки. Мы говорим. Меняй только те поля,
// которые я обновляю
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Не указан цвет носков")
    private String color;

    @Min(value = 0, message = "Процент хлопка не может быть отрицательным")
    @Max(value = 100,message = "Процента хлопка не может быть больше 100%")
    @Column
    private Byte cottonPart;

    @Column
    @NotNull(message = "Нельзя добавить или списать нулевое количество носков")
    @Min(value = 1,message = "Количество пар носков не может быть меньше 0")
    private int quantity;

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", color=" + color +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return Objects.equals(color, socks.color) && Objects.equals(cottonPart, socks.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
