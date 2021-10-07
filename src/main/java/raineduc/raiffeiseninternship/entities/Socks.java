package raineduc.raiffeiseninternship.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 1, max = 100)
    @Column(nullable = false)
    private String color;
    @Min(0)
    @Max(100)
    @Column(name="cotton_part", nullable = false)
    private Byte cottonPart;

    @Column(nullable = false)
    @Min(0)
    private Integer quantity;

    public Socks() {};

    public Socks(String color, byte cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(Byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
