package ru.simbial.cibinternstesttask.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SocksRequestData {

    //  @NotBlank
    private String color;

    /*  @Range(min=0, max = 100, message = "Value must be >=0 and <=100")
      @NotNull*/
    private Integer cottonPart;

    /*  @NotNull
      @Range(min=0, message = "Quantity can't be negative")*/
    private Long quantity;
}
