package org.vetirdoit.sock.registration.dtos.utils;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.domain.Color;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.vetirdoit.sock.registration.dtos.utils.DtoConverter.StringToBiPredicateConverter;
import static org.vetirdoit.sock.registration.dtos.utils.DtoConverter.StringToColorConverter;

class DtoConverterTest {

    private <T,R> void testConverter(Map<T, R> samples, Converter<T, R> converter) {
        samples.forEach(
                (sample, expected) ->  assertThat(converter.convert(sample)).isEqualTo(expected)
        );
    }

    @Test
    public void testStringToEnumConversion() {
        Map<String, BiPredicate> predicateSamples = Map.of(
                "greaterThan", BiPredicate.GREATER_THAN,
                "lessThan", BiPredicate.LESS_THAN,
                "equal", BiPredicate.EQUAL
        );
        Map<String, Color> colourSamples = Map.of(
                "red", Color.RED,
                "green", Color.GREEN,
                "orange", Color.ORANGE
        );
        testConverter(predicateSamples, new StringToBiPredicateConverter());
        testConverter(colourSamples, new StringToColorConverter());

    }

    @Test
    public void testFromCamelToSnakeConversion() {
        Map<String, String> samples = Map.of(
                "a", "A",
                "aB", "A_B",
                "aBC", "A_B_C",
                "aaaBbbCcc", "AAA_BBB_CCC"
        );
        samples.forEach( (camelCase, expected) ->
                assertThat( DtoConverter.fromCamelToUpperSnakeCase(camelCase) ).isEqualTo(expected)
        );
    }

}