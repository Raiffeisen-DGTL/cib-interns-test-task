package ru.raiff.raiffsocksstore.rest.converter;

import ru.raiff.raiffsocksstore.entity.SocksCounter;
import ru.raiff.raiffsocksstore.rest.dto.SocksCounterDto;

public class SocksCounterConverter {

    public static SocksCounterDto toDto(SocksCounter model) {
        return SocksCounterDto.builder()
                .quantity(model.getQuantity())
                .color(model.getCategory().getColor())
                .cottonPart(model.getCategory().getCottonPart())
                .build();
    }
}
