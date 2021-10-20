package ru.samusev.api.sercvice;

import ru.samusev.api.dto.SocksRequest;
import ru.samusev.api.dto.SocksResponse;

public interface SocksService {
    Long getQuantityByCriterion(String color, Integer cottonPart, String operation);

    SocksResponse income(SocksRequest request);

    SocksResponse outcome(SocksRequest request);
}
