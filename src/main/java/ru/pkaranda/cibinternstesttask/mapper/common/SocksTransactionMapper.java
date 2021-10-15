package ru.pkaranda.cibinternstesttask.mapper.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.pkaranda.cibinternstesttask.mapper.BaseMapper;
import ru.pkaranda.cibinternstesttask.model.domain.SocksTransaction;
import ru.pkaranda.cibinternstesttask.model.dto.SocksTransactionDTO;

@Mapper(componentModel = "spring")
public interface SocksTransactionMapper extends BaseMapper<SocksTransaction, SocksTransactionDTO> {

    SocksTransactionDTO domainToDto(SocksTransaction domain);
}
