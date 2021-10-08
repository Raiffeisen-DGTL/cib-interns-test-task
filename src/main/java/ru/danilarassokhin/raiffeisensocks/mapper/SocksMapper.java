package ru.danilarassokhin.raiffeisensocks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

@Mapper(componentModel = "spring")
public interface SocksMapper {

    SocksMapper INSTANCE = Mappers.getMapper(SocksMapper.class);

    Socks incomeDtoToSocks(SocksIncomeDto socksIncomeDto);

}
