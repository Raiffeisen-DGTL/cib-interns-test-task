package ru.vsu.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.db.entity.Socks;
import ru.vsu.service.model.SocksDto;

@Mapper
public interface SocksMapper {

  @Mapping(source = "entity.id.color", target = "color")
  @Mapping(source = "entity.id.cottonPart", target = "cottonPart")
  SocksDto fromEntity(Socks entity);

  @Mapping(target = "id.color", source = "dto.color")
  @Mapping(target = "id.cottonPart", source = "dto.cottonPart")
  Socks toEntity(SocksDto dto);
}
