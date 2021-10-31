package socks_accounting.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "sock")
@IdClass(SockId.class)
public class Sock {
    @Id
    @NotBlank
    @Size(max = 20)
    private String color;

    @Id
    @Min(0)
    @Max(100)
    private int cottonPart;

    @Min(1)
    private int quantity;

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
