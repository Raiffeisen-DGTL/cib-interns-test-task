package ru.raiffeisen.socks.service;

import org.springframework.stereotype.Service;
import ru.raiffeisen.socks.data.entity.Socks;
import ru.raiffeisen.socks.data.repository.ColorRepository;
import ru.raiffeisen.socks.data.repository.SocksRepository;

import javax.transaction.Transactional;

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
        }, () -> socksRepository.save(socks));
    }

    public void outcome(Socks socks) {
        Socks socksFromDB = socksRepository.findByCottonPartAndColorName(socks.getCottonPart(), socks.getColor().getName())
                .orElseThrow(RuntimeException::new); //Ошибка носки не найдены
        if (socks.getQuantity() > socksFromDB.getQuantity()) {
            //Ошибка недостаточно носков
        } else {
            socksFromDB.setQuantity(socksFromDB.getQuantity() - socks.getQuantity());
            socksRepository.save(socksFromDB);
        }
    }

    public long socks() {

        return 0L;
    }

}
