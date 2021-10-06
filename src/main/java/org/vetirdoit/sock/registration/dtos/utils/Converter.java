package org.vetirdoit.sock.registration.dtos.utils;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.vetirdoit.sock.registration.domain.entities.SockType;
import org.vetirdoit.sock.registration.dtos.SockTypeDto;

import java.util.regex.Pattern;

public class Converter {

    public static String fromCamelToUpperSnakeCase(String name) {
        return Pattern.compile("\\B[A-Z]")
                .matcher(name)
                .replaceAll(m -> "_" + m.group())
                .toUpperCase();
    }

    public static SockType toSockType(SockTypeDto sockTypeDto) {
        try {
            return SockType.createSockType(
                    sockTypeDto.getColor(),
                    sockTypeDto.getCottonPart(),
                    sockTypeDto.getQuantity()
            );
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
