package ru.raiffeisen.dgtl.cib.intern.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raiffeisen.dgtl.cib.intern.task.Operation;
import ru.raiffeisen.dgtl.cib.intern.task.SocksRepository;
import ru.raiffeisen.dgtl.cib.intern.task.entity.Socks;
import ru.raiffeisen.dgtl.cib.intern.task.entity.SocksId;
import ru.raiffeisen.dgtl.cib.intern.task.exception.NotEnoughSocksQuantityException;
import ru.raiffeisen.dgtl.cib.intern.task.exception.NotFoundSocks;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public Long quantity(SocksId socksId, Operation operation) {
        List<Socks> socks = Collections.emptyList();
        if (operation == Operation.LESSTHAN)
            socks = socksRepository.findAllByColorAndCottonPartLessThan(socksId.getColor(), socksId.getCottonPart());
        if (operation == Operation.MORETHAN)
            socks = socksRepository.findAllByColorAndCottonPartGreaterThan(socksId.getColor(), socksId.getCottonPart());
        if (operation == Operation.EQUAL)
            socks = socksRepository.findAllByColorAndCottonPartEquals(socksId.getColor(), socksId.getCottonPart());
        return socks.stream().map(Socks::getQuantity).reduce(Long::sum).orElse(0L);
    }

    @Override
    public void income(Socks socks) {
        Optional<Socks> optionalSocks = socksRepository.findById(new SocksId(socks.getColor(), socks.getCottonPart()));
        optionalSocks.ifPresent(value -> socks.setQuantity(value.getQuantity() + socks.getQuantity()));
        socksRepository.save(socks);
    }

    @Override
    public void outcome(Socks socks) {
        Socks socksInStock = socksRepository.findById(new SocksId(socks.getColor(), socks.getCottonPart()))
                .orElseThrow(() -> new NotFoundSocks("Socks not found!"));
        if (socksInStock.getQuantity() < socks.getQuantity())
            throw new NotEnoughSocksQuantityException("Not enough socks in stock!");
        socks.setQuantity(socksInStock.getQuantity() - socks.getQuantity());
        socksRepository.save(socks);
    }
}
