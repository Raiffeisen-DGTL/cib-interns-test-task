package ru.danilarassokhin.raiffeisensocks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksColor;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;

import java.util.List;
import java.util.Set;

@Service
public class SocksServiceImpl implements SocksService {

    private SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public List<Socks> findByColorAndCottonPartGreaterThan(SocksColor color, byte cottonPart) {
        return null;
    }

    @Override
    public List<Socks> findByColorAndCottonPartLessThan(SocksColor color, byte cottonPart) {
        return null;
    }

    @Override
    public List<Socks> findByColorAndCottonPartIs(SocksColor color, byte cottonPart) {
        return null;
    }

    @Override
    public void income(SocksIncomeDto socksIncomeDto) {

    }
}
