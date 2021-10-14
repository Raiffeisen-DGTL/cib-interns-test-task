package ru.task.socks.service;

import ru.task.socks.exception.SocksCustomException;
import ru.task.socks.model.dto.SocksDTO;
import ru.task.socks.model.dto.WarehouseDTO;

public interface SocksWarehouseService {

    void socksIncome(SocksDTO socksDTO) throws SocksCustomException;

    void socksOutcome(SocksDTO socksDTO) throws SocksCustomException;

    Long getSocksQuantityByParams(WarehouseDTO warehouseDTO) throws SocksCustomException;
}
