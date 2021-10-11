package ru.danilarassokhin.raiffeisensocks.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Represents socks entity in database
 */
@Entity(name = "socks")
@IdClass(SocksId.class)
public class Socks {

    @Id
    @Column(name = "color")
    @NotBlank(message = "Socks color can't be empty!")
    private String color;

    @Id
    @Column(name = "cotton_part")
    @Min(value = 0, message = "Socks cotton part must be greater or equals to zero")
    @Max(value = 100, message = "Socks cotton part must be less or equals to 100")
    private byte cottonPart;

    @Column(name = "quantity")
    @Min(value = 0, message = "Socks quantity can't be less than zero")
    private Long quantity;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(Long quantity) {
        this.quantity += quantity;
    }

    @Override
    public int hashCode() {
        return getColor().hashCode() + getCottonPart();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Socks socks = (Socks) obj;
        return socks.getColor().equals(this.getColor())
                && socks.getCottonPart() == this.getCottonPart();
    }
}
