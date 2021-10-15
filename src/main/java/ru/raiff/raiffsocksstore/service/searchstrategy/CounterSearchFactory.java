package ru.raiff.raiffsocksstore.service.searchstrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.raiff.raiffsocksstore.entity.enums.ComparingOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CounterSearchFactory {
    private Map<ComparingOperation, CounterSearchStrategy> searchStrategys;

    @Autowired
    public CounterSearchFactory(Set<CounterSearchStrategy> strategySet) {
        searchStrategys = new HashMap<>();
        strategySet.forEach(strategy -> searchStrategys.put(strategy.getType(), strategy));
    }

    public CounterSearchStrategy findStrategy(ComparingOperation operation) {
        return searchStrategys.getOrDefault(operation, null);
    }
}
