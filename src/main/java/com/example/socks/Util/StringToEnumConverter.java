package com.example.socks.Util;

import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Operations> {
    @Override
    public Operations convert(String source) {
            return Operations.valueOf(source.toUpperCase());
    }
}
