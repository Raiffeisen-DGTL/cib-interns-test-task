package ru.danilarassokhin.raiffeisensocks.service;

import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

public interface SocksService {

    Socks findByColorAndCottonPartGreaterThan(String color, byte cottonPart);
    Socks findByColorAndCottonPartLessThan(String color, byte cottonPart);
    Socks findByColorAndCottonPartIs(String color, byte cottonPart);
    void income(SocksIncomeDto socksIncomeDto) throws DataValidityException;
    void outcome(SocksIncomeDto socksIncomeDto) throws DataValidityException, DataNotExistsException;
}
