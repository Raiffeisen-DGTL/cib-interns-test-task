package ru.raiffeisen.api.socks.service;

import ru.raiffeisen.api.socks.Body;
import ru.raiffeisen.api.socks.enums.Operations;
import ru.raiffeisen.api.socks.entity.Socks;

import java.util.List;

public interface SocksService {

    List<Socks> getAllDefinedOperation(String color, Operations operationEnum, int cottonPart);

    Socks socksIncome(Body body);

    Socks socksOutcome(Body body);

    Socks create(String color, int cottonPart, int quantity);
}
