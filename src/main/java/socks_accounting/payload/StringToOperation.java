package socks_accounting.payload;

import org.springframework.core.convert.converter.Converter;

/**
 * Custom converter from String to Operation enum.
 */
public class StringToOperation implements Converter<String, Operation> {
    @Override
    public Operation convert(String source) {
        switch (source) {
            case "lessThan":
                return Operation.LESSTHAN;
            case "moreThan":
                return Operation.MORETHAN;
            case "equal":
                return Operation.EQUAL;
            default:
                // incorrect operation
                return Operation.NO;
        }
    }
}
