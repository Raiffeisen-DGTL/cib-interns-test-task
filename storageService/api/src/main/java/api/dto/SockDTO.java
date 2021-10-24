package api.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
public class SockDTO {

    private String color;

    @Min(0) @Max(100)
    private int cottonPart;

    @Positive
    private int quantity;
}
