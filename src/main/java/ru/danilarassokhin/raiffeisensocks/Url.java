package ru.danilarassokhin.raiffeisensocks;

import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;

/**
 * Contains all url mappings.
 */
public interface Url {

  /**
   * Global api endpoint.
   */
  String API_ENDPOINT = "/api";

  /**
   * Contains endpoints for
   * {@link ru.danilarassokhin.raiffeisensocks.controller.SocksController}.
   */
  interface Socks {

    /**
     * Endpoint for
     * {@link ru.danilarassokhin.raiffeisensocks.controller.SocksController#countSocks(String, String, Byte)}.
     */
    String ENDPOINT = "/socks";

    /**
     * Endpoint for
     * {@link ru.danilarassokhin.raiffeisensocks.controller.SocksController#incomeSocks(SocksIncomeDto)}.
     */
    String INCOME = "/income";

    /**
     * Endpoint for
     * {@link ru.danilarassokhin.raiffeisensocks.controller.SocksController#outcomeSocks(SocksOutcomeDto)}.
     */
    String OUTCOME = "/outcome";
  }

}
