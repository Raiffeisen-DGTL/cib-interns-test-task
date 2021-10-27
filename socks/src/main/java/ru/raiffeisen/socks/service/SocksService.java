package ru.raiffeisen.socks.service;

import org.springframework.stereotype.Service;
import ru.raiffeisen.socks.entity.Color;
import ru.raiffeisen.socks.entity.Socks;
import ru.raiffeisen.socks.enums.Operation;
import ru.raiffeisen.socks.exception.ColorNotFoundException;
import ru.raiffeisen.socks.exception.NotEnoughSocksException;
import ru.raiffeisen.socks.exception.SocksNotFoundException;
import ru.raiffeisen.socks.repository.ColorRepository;
import ru.raiffeisen.socks.repository.SocksRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class SocksService {

    private final SocksRepository socksRepository;
    private final ColorRepository colorRepository;

    public SocksService(SocksRepository socksRepository, ColorRepository colorRepository) {
        this.socksRepository = socksRepository;
        this.colorRepository = colorRepository;
    }

    @Transactional
    public void income(String color, int cottonPart, long quantity) {
        socksRepository.findByCottonPartAndColorName(cottonPart, color).ifPresentOrElse(socksFromDB -> {
            socksFromDB.setQuantity(socksFromDB.getQuantity() + quantity);
            socksRepository.save(socksFromDB);
        }, () -> {
            Color colorFromDB = colorRepository.findByName(color).orElseThrow(() -> new ColorNotFoundException(color));
            socksRepository.save(new Socks(cottonPart, quantity, colorFromDB));
        });
    }

    @Transactional
    public void outcome(String color, int cottonPart, long quantity) {
        Socks socksFromDB = socksRepository.findByCottonPartAndColorName(cottonPart, color)
                .orElseThrow(() -> new SocksNotFoundException(color, cottonPart));
        if (quantity > socksFromDB.getQuantity()) {
            throw new NotEnoughSocksException(quantity, socksFromDB.getQuantity());
        } else {
            socksFromDB.setQuantity(socksFromDB.getQuantity() - quantity);
            socksRepository.save(socksFromDB);
        }
    }

    public long socks(String color, String op, int cottonPart) {
        Operation operation = Operation.decode(op);
        List<Socks> socks = Collections.emptyList();
        switch (operation) {
            case MORE_THAN:
                socks = socksRepository.findByColorNameAndCottonPartGreaterThan(color, cottonPart);
                break;
            case LESS_THAN:
                socks = socksRepository.findByColorNameAndCottonPartLessThan(color, cottonPart);
                break;
            case EQUAL:
                socks = socksRepository.findByColorNameAndCottonPartEquals(color, cottonPart);
                break;
        }
        return socks.stream().map(Socks::getQuantity).reduce(0L, Long::sum);
    }

}
