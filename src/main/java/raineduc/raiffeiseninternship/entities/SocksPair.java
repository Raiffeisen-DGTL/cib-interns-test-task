package raineduc.raiffeiseninternship.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class SocksPair {
    @Size(min = 1, max = 100)
    private String color;
    @Min(0)
    @Max(100)
    private byte cottonPart;

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }
}
