package api.mappers;

import api.dto.SockDTO;
import model.models.Sock;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SockMapper {

    Sock toEntity(SockDTO dto);
}
