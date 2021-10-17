package com.example.bootcamp.service;

import com.example.bootcamp.dto.SocksDto;
import com.example.bootcamp.entity.SocksEntity;
import com.example.bootcamp.repo.SocksRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final SocksRepo socksRepo;

    public IncomeServiceImpl(SocksRepo socksRepo) {
        this.socksRepo = socksRepo;
    }

    @Transactional
    @Override
    public void income(List<SocksDto> socksDtos) {
        for (SocksDto req : socksDtos) {

            String color = req.getColor();
            int cottonPart = req.getCottonPart();
            int quantity = req.getQuantity();

            if (cottonPart < 0 || cottonPart > 100) {
                throw new RuntimeException("Неверно задано значение cotton.Значение должно быть " +
                        "целым числом в диапазоне от 0 до 100");
            }
            if (quantity < 0) {
                throw new RuntimeException("Неверно задано значение quantity.Значение должно быть больше 0");
            }

            Optional<SocksEntity> socksEntity = socksRepo.fingByColorAndCotton(color, cottonPart);

            if (socksEntity.isPresent()) {
                SocksEntity socksPresent = socksEntity.get();
                socksPresent.setQuantityEntity(quantity);
                socksRepo.save(socksPresent);
            } else {
                socksRepo.save(new
                        SocksEntity(color, cottonPart, quantity));
            }
        }
    }
}
