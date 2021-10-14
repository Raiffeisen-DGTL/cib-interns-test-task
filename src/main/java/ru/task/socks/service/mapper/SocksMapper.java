package ru.task.socks.service.mapper;

import org.springframework.stereotype.Component;
import ru.task.socks.model.dto.SocksDTO;
import ru.task.socks.model.entity.SocksEntity;

@Component
public class SocksMapper {

    public SocksEntity dtoToEntity(SocksDTO socksDTO) {
        if (socksDTO == null) {
            return null;
        }
        return new SocksEntity(socksDTO.getId(),
                socksDTO.getColor(),
                socksDTO.getCottonPart(),
                socksDTO.getQuantity());
    }
}
