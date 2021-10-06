package org.vetirdoit.sock.registration.dtos;

import lombok.Getter;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.dtos.utils.Converter;

@Getter
public class ColorDto {
    private final Color color;

    public ColorDto(String  colorStr) {
        color =  Color.valueOf( Converter.fromCamelToUpperSnakeCase(colorStr) ) ;
    }
}
