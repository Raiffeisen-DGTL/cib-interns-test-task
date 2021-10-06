package org.vetirdoit.sock.registration.dtos.utils;

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
        return SockType.createSockType(
                sockTypeDto.getColor(),
                sockTypeDto.getCottonPart(),
                sockTypeDto.getQuantity()
        );
    }
}
