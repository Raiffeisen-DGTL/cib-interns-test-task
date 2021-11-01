package ru.danilarassokhin.raiffeisensocks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceIncomeDto;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceSearchDto;

/**
 * Mapper for Socks data.
 */
@Mapper(componentModel = "spring")
public interface SocksMapper {

  SocksMapper INSTANCE = Mappers.getMapper(SocksMapper.class);

  /**
   * Converts {@link ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto}
   * to {@link ru.danilarassokhin.raiffeisensocks.model.Socks}.
   *
   * @param socksIncomeDto Income data to convert
   * @return Socks entity
   */
  Socks incomeDtoToSocks(SocksServiceIncomeDto socksIncomeDto);

  /**
   * Converts {@link ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto} to
   * {@link ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceIncomeDto}.
   *
   * @param socksIncomeDto Dto to convert
   * @return Service dto
   */
  SocksServiceIncomeDto controllerIncomeDtoToServiceDto(SocksIncomeDto socksIncomeDto);

  /**
   * Converts {@link ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto} to
   * {@link ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceOutcomeDto}.
   *
   * @param socksOutcomeDto Dto to convert
   * @return Service dto
   */
  SocksServiceOutcomeDto controllerOutcomeDtoToServiceDto(SocksOutcomeDto socksOutcomeDto);

  /**
   * Converts {@link ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto} to
   * {@link ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceSearchDto}.
   *
   * @param socksSearchDto Dto to convert
   * @return Service dto
   */
  SocksServiceSearchDto controllerSearchDtoToServiceDto(SocksSearchDto socksSearchDto);

}
