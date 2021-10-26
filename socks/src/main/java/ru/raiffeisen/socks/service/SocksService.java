package ru.raiffeisen.socks.service;

import org.springframework.stereotype.Service;
import ru.raiffeisen.socks.entity.Socks;
import ru.raiffeisen.socks.enums.Operation;
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
    public void income(Socks socks) {
        socksRepository.findByCottonPartAndColorName(socks.getCottonPart(), socks.getColor().getName()).ifPresentOrElse(socksFromDB -> {
            socksFromDB.setQuantity(socksFromDB.getQuantity() + socks.getQuantity());
            socksRepository.save(socksFromDB);
        }, () -> {
            colorRepository.findByName(socks.getColor().getName()).orElseThrow(RuntimeException::new); //Ошибка при отсутствии цевета
            socksRepository.save(socks);
        });
    }

    public void outcome(Socks socks) {
        Socks socksFromDB = socksRepository.findByCottonPartAndColorName(socks.getCottonPart(), socks.getColor().getName())
                .orElseThrow(RuntimeException::new); //Ошибка носки не найдены
        if (socks.getQuantity() > socksFromDB.getQuantity()) {
            throw new RuntimeException(); //Ошибка недостаточно носков
        } else {
            socksFromDB.setQuantity(socksFromDB.getQuantity() - socks.getQuantity());
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
