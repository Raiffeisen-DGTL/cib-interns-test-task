package ru.raif.socks.service;

import org.springframework.stereotype.Service;
import ru.raif.socks.request.Comparison;
import ru.raif.socks.request.SocksIncomeOutcomeRq;

@Service
public interface SocksService {
    void income(SocksIncomeOutcomeRq request);

    void outcome(SocksIncomeOutcomeRq request);

    Integer search(String color, Comparison operation, Integer cottonPart);
}
