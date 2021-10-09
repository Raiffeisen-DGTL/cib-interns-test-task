package ru.strelchm.raiffeisenchallenge.service;

import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.dto.InOutComeSockDto;
import ru.strelchm.raiffeisenchallenge.dto.SockCriteria;

import java.util.List;

public interface SockService {

    Sock sockOutcome(InOutComeSockDto incomeSockDto);

    Sock sockIncome(InOutComeSockDto incomeSockDto);

    List<Sock> getAll(SockCriteria sockCriteria);
}
