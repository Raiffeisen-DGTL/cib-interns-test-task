package org.vetirdoit.sock.registration.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.vetirdoit.sock.registration.domain.Color;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter @AllArgsConstructor
public class SockTypeDto {

    private Color color;

    @Min(0) @Max(100)
    private int cottonPart;

    @Positive
    private int quantity;

}
