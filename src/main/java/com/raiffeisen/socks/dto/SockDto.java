package com.raiffeisen.socks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SockDto {
    @JsonProperty("color")
    private String color;
    @JsonProperty("cottonPart")
    private Integer cottonPart;
    @JsonProperty("quantity")
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SockDto sockDto = (SockDto) o;
        return Objects.equals(color, sockDto.color) &&
                Objects.equals(cottonPart, sockDto.cottonPart) &&
                Objects.equals(quantity, sockDto.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart, quantity);
    }
}
