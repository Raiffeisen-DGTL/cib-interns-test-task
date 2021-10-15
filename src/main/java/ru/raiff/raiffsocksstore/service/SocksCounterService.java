package ru.raiff.raiffsocksstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raiff.raiffsocksstore.entity.SocksCategory;
import ru.raiff.raiffsocksstore.entity.SocksCounter;
import ru.raiff.raiffsocksstore.entity.enums.ComparingOperation;
import ru.raiff.raiffsocksstore.exception.CategoryNotFoundException;
import ru.raiff.raiffsocksstore.exception.SocksQuantityOutOfBoundsException;
import ru.raiff.raiffsocksstore.exception.StrategyNotFoundException;
import ru.raiff.raiffsocksstore.repository.SocksCounterRepository;
import ru.raiff.raiffsocksstore.rest.dto.SocksCounterDto;
import ru.raiff.raiffsocksstore.service.searchstrategy.CounterSearchFactory;

import java.util.List;

import static ru.raiff.raiffsocksstore.rest.converter.SocksCounterConverter.toDto;

@Service
@RequiredArgsConstructor
public class SocksCounterService {

    private final SocksCounterRepository repository;
    private final CounterSearchFactory factory;

    public SocksCounterDto income(SocksCounterDto dto) {
        var socksCounter =
                repository.findByColorAndCottonPartWithCounter(dto.getColor(), dto.getCottonPart())
                        .orElseThrow(CategoryNotFoundException::new);

        Integer oldQuantity = socksCounter.getQuantity();
        socksCounter.setQuantity(oldQuantity + dto.getQuantity());

        return toDto(repository.save(socksCounter));
    }

    public SocksCounterDto outcome(SocksCounterDto dto) {
        var socksCounter =
                repository.findByColorAndCottonPartWithCounter(dto.getColor(), dto.getCottonPart())
                        .orElseThrow(CategoryNotFoundException::new);

        Integer oldQuantity = socksCounter.getQuantity();
        if (oldQuantity >= dto.getQuantity()) {
            socksCounter.setQuantity(oldQuantity - dto.getQuantity());
        } else throw new SocksQuantityOutOfBoundsException(oldQuantity, dto.getQuantity());

        return toDto(repository.save(socksCounter));
    }

    public Long getAllByColorAndCottonPartComparing(String color,
                                                                     Short cottonPart,
                                                                     ComparingOperation operation) {
        var strategy = factory.findStrategy(operation);
        if (operation != null) {
            return strategy.getAllByColorAndCottonPart(color, cottonPart);
        } else throw new StrategyNotFoundException(operation);
    }

    public void createCategoryCounter(SocksCategory category) {
        repository.save(SocksCounter.builder()
                .quantity(0)
                .category(category)
                .build());
    }
}
