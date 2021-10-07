package com.example.testtask.factories;

import com.example.testtask.dto.SocksDto;
import com.example.testtask.store.entities.SocksEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SocksDtoFactory {

    public SocksDto makeSocksDto(SocksEntity entity) {
        return SocksDto.builder()
                //.id(entity.getId())
                .color(entity.getColor())
                .cottonPart(entity.getCottonPart())
                .quantity(entity.getQuantity())
                .build();
    }
}
