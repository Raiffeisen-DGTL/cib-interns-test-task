package ru.strelchm.raiffeisenchallenge.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.dto.SockDto;

@Mapper
public interface SockMapper {
    SockMapper INSTANCE = Mappers.getMapper(SockMapper.class);

    SockDto toDto(Sock sock);
}
