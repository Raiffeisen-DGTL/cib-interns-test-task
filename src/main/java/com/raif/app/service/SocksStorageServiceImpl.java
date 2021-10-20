package com.raif.app.service;

import com.raif.app.dao.model.SocksStorage;
import com.raif.app.controller.dto.SocksIncomeDTO;
import com.raif.app.controller.dto.SocksOutcomeDTO;
import com.raif.app.dao.repository.SocksStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocksStorageServiceImpl implements SocksStorageService {

    private final SocksStorageRepository repository;

    @Override
    public void registerIncome(SocksIncomeDTO socksIncomeDTO) {
        SocksStorage socksStorage = repository.findByColorAndCottonPart(socksIncomeDTO.getColor(), socksIncomeDTO.getCottonPart());
        if (socksStorage != null) {
            repository.updateQuantity(socksStorage.getId(), socksStorage.getQuantity() + socksIncomeDTO.getQuantity());
        } else {
            repository.insert(new SocksStorage(null, socksIncomeDTO.getColor(),  socksIncomeDTO.getQuantity(), socksIncomeDTO.getCottonPart()));
        }
    }

    @Override
    public void registerOutcome(SocksOutcomeDTO outcomeDTO) {
        SocksStorage socksStorage = repository.findByColorAndCottonPart(outcomeDTO.getColor(), outcomeDTO.getCottonPart());
        if (socksStorage != null) {
            if (outcomeDTO.getQuantity() > socksStorage.getQuantity()) {
                String text = String.format("Сумма отпуска %s превышает текущую на складе %s", outcomeDTO.getQuantity(), socksStorage.getQuantity());
                throw new ConstraintViolationException(text, null);
            }
            repository.updateQuantity(socksStorage.getId(), socksStorage.getQuantity() - outcomeDTO.getQuantity());
        } else {
            repository.insert(new SocksStorage(null, outcomeDTO.getColor(),  outcomeDTO.getQuantity(), outcomeDTO.getCottonPart()));
        }
    }

    @Override
    public Long find(SocksStorageRequestFilter filter) {
        Long count = 0L;
        switch (filter.getOperation()) {
            case equal:
                SocksStorage socksStorage = repository.findByColorAndCottonPart(filter.getColor(), filter.getCottonPart());
                if (socksStorage != null) {
                    return socksStorage.getQuantity();
                }
                return 0L;
            case moreThan:
                List<SocksStorage> listGreaterThan = repository.findAllByColorAndCottonPartIsGreaterThan(filter.getColor(), filter.getCottonPart());
                for (SocksStorage storage : listGreaterThan) {
                    count += storage.getQuantity();
                }
                return count;
            case lessThan:
                List<SocksStorage> lessThanList = repository.findAllByColorAndCottonPartIsLessThan(filter.getColor(), filter.getCottonPart());
                for (SocksStorage lessThanStorage : lessThanList) {
                    count += lessThanStorage.getQuantity();
                }
                return count;
        }
        return 0L;
    }
}
