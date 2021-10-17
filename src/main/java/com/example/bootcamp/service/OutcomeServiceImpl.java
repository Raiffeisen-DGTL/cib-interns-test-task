package com.example.bootcamp.service;

import com.example.bootcamp.dto.SocksDto;
import com.example.bootcamp.entity.SocksEntity;
import com.example.bootcamp.repo.SocksRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OutcomeServiceImpl implements OutcomeService {

    private final SocksRepo socksRepo;

    public OutcomeServiceImpl(SocksRepo socksRepo) {
        this.socksRepo = socksRepo;
    }

    @Transactional
    @Override
    public void outcome(List<SocksDto> socksDtos) {
        for (SocksDto req : socksDtos) {

            String color = req.getColor();
            int cottonPart = req.getCottonPart();
            int quantity = req.getQuantity();

            if (quantity < 0) {
                throw new RuntimeException("Неверно задано значение quantity.Значение должно быть больше 0");
            }

            List<SocksEntity> byColor = socksRepo.findByColor(color);

            if (byColor.isEmpty()) {
                throw new RuntimeException("Носки с цветом " + color + " не найдены");
            } else {
                Optional<SocksEntity> socksEntity = socksRepo.fingByColorAndCotton(color, cottonPart);

                if (socksEntity.isEmpty()) {
                    throw new RuntimeException("Носки с процентным содержанием хлопка "
                            + cottonPart + " не найдены");
                } else {
                    SocksEntity socks = socksEntity.get();
                    int quantityEntity = socks.getQuantityEntity();

                    if (quantityEntity > quantity) {
                        socks.setQuantityEntity(-quantity);
                        socksRepo.save(socks);
                    } else if (quantityEntity == quantity) {
                        socksRepo.delete(socks);
                    } else {
                        throw new RuntimeException("На складе нет необходимого количества носков цвета "
                                + color + " и процентным содержанием хлопка " + cottonPart);
                    }
                }
            }
        }
    }
}
