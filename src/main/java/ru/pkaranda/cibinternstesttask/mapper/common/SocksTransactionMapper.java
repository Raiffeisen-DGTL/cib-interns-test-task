package ru.pkaranda.cibinternstesttask.mapper.common;

import org.mapstruct.Mapper;
import ru.pkaranda.cibinternstesttask.mapper.BaseMapper;
import ru.pkaranda.cibinternstesttask.model.domain.SocksTransaction;
import ru.pkaranda.cibinternstesttask.model.dto.SocksTransactionDTO;

@Mapper(componentModel = "spring")
public interface SocksTransactionMapper extends BaseMapper<SocksTransaction, SocksTransactionDTO> {
}
