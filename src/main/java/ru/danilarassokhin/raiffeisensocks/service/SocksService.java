package ru.danilarassokhin.raiffeisensocks.service;

import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.exception.InternalException;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

public interface SocksService {

    /**
     * Gets socks for given color and cotton part
     * @param color Socks color
     * @param cottonPart Socks cotton part
     * @return Socks or null if nothing exists satisfying condition
     */
    Socks findByColorAndCottonPartIs(String color, byte cottonPart);

    /**
     * Adds new socks to stock
     * @param socksIncomeDto Income data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto}
     * @return Current quantity of given socks color and cotton part
     * @throws DataValidityException If request data is not valid
     * @throws InternalException If internal exception occurred
     */
    SocksIncomeDto income(SocksIncomeDto socksIncomeDto) throws DataValidityException, InternalException;

    /**
     * Removes some socks from stock
     * @param socksOutcomeDto Outcome data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto}
     * @return Current quantity of given socks color and cotton part
     * @throws DataValidityException If request data is not valid
     * @throws DataNotExistsException If socks of given color and cotton part is not exists
     * @throws InternalException If internal exception occurredd
     */
    SocksOutcomeDto outcome(SocksOutcomeDto socksOutcomeDto) throws DataValidityException,
            DataNotExistsException, InternalException;

    /**
     * Counts socks of given color and cotton part condition
     * @param socksSearchDto Counting data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto}
     * @return Number of socks in stock for given condition
     * @throws DataValidityException If counting data is not valid
     */
    Long countSocks(SocksSearchDto socksSearchDto) throws DataValidityException;
}
