package org.vetirdoit.sock.registration.dtos;

import lombok.Getter;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.dtos.utils.Converter;

@Getter
public class BiPredicateDto {
    private final BiPredicate operation;

    public BiPredicateDto(String  predicateStr) {
        operation =  BiPredicate.valueOf( Converter.fromCamelToUpperSnakeCase(predicateStr) ) ;
    }
}
