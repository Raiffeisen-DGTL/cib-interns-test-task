package org.vetirdoit.sock.registration.dtos.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ConverterTest {

    @Test
    public void testFromCamelToSnakeConversion() {
        Map<String, String> samples = Map.of(
                "a", "A",
                "aB", "A_B",
                "aBC", "A_B_C",
                "aaaBbbCcc", "AAA_BBB_CCC"
        );
        samples.forEach( (camelCase, expected) ->
                assertThat( Converter.fromCamelToUpperSnakeCase(camelCase) ).isEqualTo(expected)
        );
    }

}