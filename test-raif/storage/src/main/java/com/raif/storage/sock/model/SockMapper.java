package com.raif.storage.sock.model;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class SockMapper {

    private final ModelMapper modelMapper;

    public SockDto toDto(Sock entity) {
        log.debug("Converting entity {{}} to dto", entity.toString());
        return modelMapper.map(entity, SockDto.class);
    }

    public Sock toEntity(SockDto dto) {
        log.debug("Converting dto {{}} to entity", dto.toString());
        return modelMapper.map(dto, Sock.class);
    }
}
