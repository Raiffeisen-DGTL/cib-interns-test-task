package raineduc.raiffeiseninternship.services.dto;

import javax.validation.constraints.*;


public class SocksBatch {
    @Size(min = 1, max = 100)
    @NotNull
    private String color;
    @NotNull
    @Min(0)
    @Max(100)
    private Byte cottonPart;
    @Positive
    @NotNull
    private Integer quantity;

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
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
