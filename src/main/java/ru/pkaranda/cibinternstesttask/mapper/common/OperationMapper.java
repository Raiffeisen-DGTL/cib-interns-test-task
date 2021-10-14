package ru.pkaranda.cibinternstesttask.mapper.common;

import org.mapstruct.Mapper;
import ru.pkaranda.cibinternstesttask.mapper.BaseMapper;
import ru.pkaranda.cibinternstesttask.model.domain.Operation;
import ru.pkaranda.cibinternstesttask.model.dto.OperationDTO;

@Mapper(componentModel = "spring")
public interface OperationMapper extends BaseMapper<Operation, OperationDTO> {
}
