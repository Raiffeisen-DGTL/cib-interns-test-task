package ru.raiffeisen.dgtl.cib.intern.task;

import org.springframework.core.convert.converter.Converter;
import ru.raiffeisen.dgtl.cib.intern.task.exception.NotFoundOperation;

public class StringToEnumConverter implements Converter<String, Operation> {

    @Override
    public Operation convert(String source) {
        try {
            return Operation.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundOperation("Incorrect operation!");
        }
    }
}
