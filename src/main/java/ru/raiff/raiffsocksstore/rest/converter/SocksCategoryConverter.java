package ru.raiff.raiffsocksstore.rest.converter;

import ru.raiff.raiffsocksstore.entity.SocksCategory;
import ru.raiff.raiffsocksstore.rest.dto.SocksCategoryDto;

public class SocksCategoryConverter {

    public static SocksCategory toModel(SocksCategoryDto dto) {
        return SocksCategory.builder()
                .id(null)
                .color(dto.getColor())
                .cottonPart(dto.getCottonPart())
                .build();
    }

    public static SocksCategoryDto toDto(SocksCategory model) {
        return SocksCategoryDto.builder()
                .color(model.getColor())
                .cottonPart(model.getCottonPart())
                .build();
    }
}
