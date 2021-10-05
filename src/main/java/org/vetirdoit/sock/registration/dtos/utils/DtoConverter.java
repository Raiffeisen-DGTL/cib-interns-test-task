package org.vetirdoit.sock.registration.dtos.utils;

import org.springframework.core.convert.converter.Converter;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.domain.entities.SockType;
import org.vetirdoit.sock.registration.dtos.SockTypeDto;

import java.util.regex.Pattern;

public class DtoConverter {

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

    public static class StringToColorConverter implements Converter<String, Color> {
        @Override
        public Color convert(String str) {
            return Color.valueOf(fromCamelToUpperSnakeCase(str) );
        }
    }

    public static class StringToBiPredicateConverter implements Converter<String, BiPredicate> {
        @Override
        public BiPredicate convert(String str) {
            return BiPredicate.valueOf(fromCamelToUpperSnakeCase(str) );
        }
    }
}
