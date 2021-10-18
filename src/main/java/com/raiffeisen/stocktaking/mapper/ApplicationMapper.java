package com.raiffeisen.stocktaking.mapper;

import com.raiffeisen.stocktaking.dto.SocksModelDTO;
import com.raiffeisen.stocktaking.model.SocksModel;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public SocksModel mapToModel(SocksModelDTO dto) {
        return new SocksModel()
                .withColor(dto.getColor())
                .withCottonPart(dto.getCottonPart())
                .withQuantity(dto.getQuantity());
    }

    public SocksModelDTO mapToDto(SocksModel model) {
        return new SocksModelDTO()
                .setColor(model.getColor())
                .setQuantity(model.getQuantity())
                .setCottonPart(model.getCottonPart());
    }
}
