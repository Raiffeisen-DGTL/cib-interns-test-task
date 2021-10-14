package raineduc.raiffeiseninternship.services.dto;

import javax.validation.constraints.*;

public class SocksRequest {
    @Size(min = 1, max = 100)
    @NotNull
    private String color;
    @NotNull
    private Byte cottonPart;
    @Pattern(
            regexp = "^(moreThan|lessThan|equal)$",
            message = "Operation must be one of these: 'moreThan', 'lessThan' or 'equal'"
    )
    @NotNull
    private String operation;

    public String getColor() {
        return color;
    }

    public Byte getCottonPart() {
        return cottonPart;
    }

    public String getOperation() {
        return operation;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(Byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
