package ru.javabootcamp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SocksRqDto {

    @NotEmpty(message = "must not be empty")
    @Size(min = 2, max = 30, message = "must have the length between 2 and 30")
    @Pattern(regexp = "^[a-zа-я]+$", message = "must contains only latin or russian lowercase letters")
    private String color;

    @NotNull(message = "must not be empty")
    @Range(min = 0, max = 100, message = "must be between 0 and 100")
    private Integer cottonPart;

    @NotNull(message = "must not be empty")
    @Range(min = 1, max = 2147483647, message = "must be between 1 and 2147483647")
    private Integer quantity;

}
