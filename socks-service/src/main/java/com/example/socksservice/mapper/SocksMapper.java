package com.example.socksservice.mapper;

import com.example.socksservice.dto.SockDTO;
import com.example.socksservice.entity.Sock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SocksMapper {
    SockDTO fromEntity(Sock sock);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Sock fromDto(SockDTO dto);
}
