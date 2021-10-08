package ru.danilarassokhin.raiffeisensocks.service;

import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.exception.InternalException;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

public interface SocksService {

    Socks findByColorAndCottonPartGreaterThan(String color, byte cottonPart);
    Socks findByColorAndCottonPartLessThan(String color, byte cottonPart);
    Socks findByColorAndCottonPartIs(String color, byte cottonPart);
    SocksIncomeDto income(SocksIncomeDto socksIncomeDto) throws DataValidityException, InternalException;
    SocksOutcomeDto outcome(SocksOutcomeDto socksIncomeDto) throws DataValidityException,
            DataNotExistsException, InternalException;
}
