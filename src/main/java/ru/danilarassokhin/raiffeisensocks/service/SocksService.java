package ru.danilarassokhin.raiffeisensocks.service;

import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceIncomeDto;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceResponse;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceSearchDto;

/**
 * Service to operate with socks.
 */
public interface SocksService {

  /**
   * Gets socks for given color and cotton part.
   *
   * @param color      Socks color
   * @param cottonPart Socks cotton part
   * @return Socks or null if nothing exists satisfying condition
   */
  Socks findByColorAndCottonPartIs(String color, byte cottonPart);

  /**
   * Adds new socks to stock.
   *
   * @param socksIncomeDto Income data {@link ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceIncomeDto}
   * @return Current quantity of given socks color and cotton part
   */
  SocksServiceResponse income(SocksServiceIncomeDto socksIncomeDto);

  /**
   * Removes some socks from stock.
   *
   * @param socksOutcomeDto Outcome data
   *     {@link ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceOutcomeDto}
   * @return Current quantity of given socks color and cotton part
   */
  SocksServiceResponse outcome(SocksServiceOutcomeDto socksOutcomeDto);

  /**
   * Counts socks of given color and cotton part condition.
   *
   * @param socksSearchDto Counting data
   *     {@link ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceSearchDto}
   * @return Number of socks in stock for given condition
   */
  SocksServiceResponse countSocks(SocksServiceSearchDto socksSearchDto);
}
