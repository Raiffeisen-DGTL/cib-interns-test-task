package com.github.furetur.raiffeisentask.restData;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringToOperationTypeConverter implements Converter<String, OperationType> {
    @Override
    @Nullable
    public OperationType convert(String source) {
        return switch (source) {
            case "moreThan" -> OperationType.MORE_THAN;
            case "lessThan" -> OperationType.LESS_THAN;
            case "equal" -> OperationType.EQUAL;
            default -> null;
        };
    }
}
