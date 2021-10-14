package ru.task.socks.repository;

import ru.task.socks.exception.SocksCustomException;
import ru.task.socks.model.entity.SocksEntity;

public interface SocksWarehouseRepository {

    void socksIncome(SocksEntity socks);

    void socksOutcome(SocksEntity socks) throws SocksCustomException;

    Long getSocksQuantityByParams(String color, String operation, Float cotonPart) throws SocksCustomException;
}
