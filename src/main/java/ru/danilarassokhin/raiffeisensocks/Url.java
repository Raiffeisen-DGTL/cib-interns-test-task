package ru.danilarassokhin.raiffeisensocks;

import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;

/**
 * Contains all url mappings
 */
public interface Url {

    /**
     * Global api endpoint
     */
    String API_ENDPOINT = "/api";

    /**
     * Contains endpoints for {@link ru.danilarassokhin.raiffeisensocks.controller.SockController}
     */
    interface SOCKS {

        /**
         * Endpoint for {@link ru.danilarassokhin.raiffeisensocks.controller.SockController#countSocks(SocksSearchDto)}
         */
        String ENDPOINT = "/socks";

        /**
         * Endpoint for {@link ru.danilarassokhin.raiffeisensocks.controller.SockController#incomeSocks(SocksIncomeDto)}
         */
        String INCOME = "/income";

        /**
         * Endpoint for {@link ru.danilarassokhin.raiffeisensocks.controller.SockController#outcomeSocks(SocksOutcomeDto)}
         */
        String OUTCOME = "/outcome";
    }

}
