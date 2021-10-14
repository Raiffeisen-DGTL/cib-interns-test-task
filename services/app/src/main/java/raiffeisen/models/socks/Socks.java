package raiffeisen.models.socks;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

/**
 * @author voroningg
 */
@Table("Socks")
public class Socks {
    @Id
    private String color;
    private int cottonPart;
    private int quantity;

    public Socks() {
    }

    public Socks(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && quantity == socks.quantity && Objects.equals(color, socks.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart, quantity);
    }
}
