package ru.raiffeisen.api.socks.service;

import ru.raiffeisen.api.socks.OperationEnum;
import ru.raiffeisen.api.socks.entity.Socks;

import java.util.List;

public interface SocksService {

    List<Socks> getAllDefinedOperation(String color, OperationEnum operationEnum, int cottonPart);

    Socks socksIncome(String color, int cottonPart, int quantity);

    Socks socksOutcome(String color, int cottonPart, int quantity);

    void save(Socks socks);

    Socks create(String color, int cottonPart, int quantity);
}
