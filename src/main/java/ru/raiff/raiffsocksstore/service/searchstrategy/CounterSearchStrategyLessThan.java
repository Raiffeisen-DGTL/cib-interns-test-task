package ru.raiff.raiffsocksstore.service.searchstrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.raiff.raiffsocksstore.entity.enums.ComparingOperation;
import ru.raiff.raiffsocksstore.repository.SocksCounterRepository;

@Component
@RequiredArgsConstructor
public class CounterSearchStrategyLessThan implements CounterSearchStrategy {

    private final SocksCounterRepository repository;

    @Override
    public Long getAllByColorAndCottonPart(String color, Short cottonPart) {
        return repository.sumByColorAndCottonPartLessThan(color, cottonPart);
    }

    @Override
    public ComparingOperation getType() {
        return ComparingOperation.lessThan;
    }
}