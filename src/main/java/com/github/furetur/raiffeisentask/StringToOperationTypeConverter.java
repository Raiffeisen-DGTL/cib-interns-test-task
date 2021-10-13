package com.github.furetur.raiffeisentask;


import com.sun.istack.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class StringToOperationTypeConverter implements Converter<String, OperationTypes> {
    @Override
    @Nullable
    public OperationTypes convert(String source) {
        return switch (source) {
            case "moreThan" -> OperationTypes.MORE_THAN;
            case "lessThan" -> OperationTypes.LESS_THAN;
            case "equal" -> OperationTypes.EQUAL;
            default -> null;
        };
    }
}
