package ru.project.raiffeisenbank.mapper;

import org.springframework.stereotype.Component;
import ru.project.raiffeisenbank.dto.IncomeResponse;
import ru.project.raiffeisenbank.dto.OutcomeResponse;
import ru.project.raiffeisenbank.entity.Socks;

@Component
public class SocksMapper {
    public IncomeResponse toIncomeResponse(Socks socksEntity) {
        IncomeResponse response = new IncomeResponse();

        response.setColor(socksEntity.getColor());
        response.setCottonPart(socksEntity.getCottonPart());
        response.setQuantity(socksEntity.getQuantity());

        return response;
    }

    public OutcomeResponse toOutcomeResponse(Socks socksEntity) {
        OutcomeResponse response = new OutcomeResponse();

        response.setColor(socksEntity.getColor());
        response.setCottonPart(socksEntity.getCottonPart());
        response.setQuantity(socksEntity.getQuantity());

        return response;
    }
}
