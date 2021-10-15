package ru.pkaranda.cibinternstesttask.mapper.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.pkaranda.cibinternstesttask.mapper.BaseMapper;
import ru.pkaranda.cibinternstesttask.model.domain.SocksTransaction;
import ru.pkaranda.cibinternstesttask.model.dto.SocksTransactionDTO;

@Mapper(componentModel = "spring")
public interface SocksTransactionMapper extends BaseMapper<SocksTransaction, SocksTransactionDTO> {

    @Override
    @Mappings({
            @Mapping(source = "domain.id", target = "id"),
            @Mapping(source = "domain.color", target = "color"),
            @Mapping(source = "domain.transactionType", target = "transactionType"),
            @Mapping(source = "domain.cottonPart", target = "cottonPart"),
            @Mapping(source = "domain.quantity", target = "quantity")
    })
    SocksTransactionDTO domainToDto(SocksTransaction domain);
}
