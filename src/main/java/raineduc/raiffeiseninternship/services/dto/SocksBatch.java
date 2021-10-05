package raineduc.raiffeiseninternship.services.dto;

import javax.validation.constraints.*;


public class SocksBatch {
    @Size(min = 1, max = 100)
    @NotNull
    private String color;
    @Min(0)
    @Max(100)
    @NotNull
    private byte cottonPart;
    @Positive
    @NotNull
    private int quantity;

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }
}
