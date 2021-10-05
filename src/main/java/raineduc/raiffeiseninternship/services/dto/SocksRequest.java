package raineduc.raiffeiseninternship.services.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SocksRequest {
    @Size(min = 1, max = 100)
    @Nullable
    private String color;
    @Min(0)
    @Max(100)
    @Nullable
    private Byte cottonPart;
    @Pattern(
            regexp = "^(moreThan|lessThan|equal)$",
            message = "Operation must be one of these: 'moreThan', 'lessThan' or 'equal'"
    )
    @Nullable
    private String operation;

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public String getOperation() {
        return operation;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
