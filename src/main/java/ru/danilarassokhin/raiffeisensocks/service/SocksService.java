package ru.danilarassokhin.raiffeisensocks.service;

import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksColor;

import java.util.List;
import java.util.Set;

public interface SocksService {

    List<Socks> findByColorAndCottonPartGreaterThan(SocksColor color, byte cottonPart);
    List<Socks> findByColorAndCottonPartLessThan(SocksColor color, byte cottonPart);
    List<Socks> findByColorAndCottonPartIs(SocksColor color, byte cottonPart);
    void income(SocksIncomeDto socksIncomeDto);
}
