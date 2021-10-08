package ru.danilarassokhin.raiffeisensocks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

/**
 * Mapper for Socks data
 */
@Mapper(componentModel = "spring")
public interface SocksMapper {

    SocksMapper INSTANCE = Mappers.getMapper(SocksMapper.class);

    /**
     * Converts {@link ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto}
     * to {@link ru.danilarassokhin.raiffeisensocks.model.Socks}
     * @param socksIncomeDto Income data to convert
     * @return Socks entity
     */
    Socks incomeDtoToSocks(SocksIncomeDto socksIncomeDto);

}
