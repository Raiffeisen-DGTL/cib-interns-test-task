package task.raif.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SocksLot {

    @NotBlank
    @Length(max = 255)
    private String color;

    @NotNull
    @Min(0)
    @Max(100)
    private int cottonPart;

    @NotNull
    @Min(0)
    private long quantity;

    public SocksLot() {
    }

    public SocksLot(String color, int cottonPart, long quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public SocksLot(SocksFilter socksFilter, long quantity) {
        this.color = socksFilter.getColor();
        this.cottonPart = socksFilter.getCottonPart();
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SocksLot{" +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
