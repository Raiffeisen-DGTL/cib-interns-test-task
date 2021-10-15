package ru.raiff.raiffsocksstore.service.searchstrategy;


import ru.raiff.raiffsocksstore.entity.enums.ComparingOperation;

public interface CounterSearchStrategy {

    Long getAllByColorAndCottonPart(String color, Short cottonPart);

    ComparingOperation getType();
}
