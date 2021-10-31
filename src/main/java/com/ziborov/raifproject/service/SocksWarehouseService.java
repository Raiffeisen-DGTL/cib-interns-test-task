package com.ziborov.raifproject.service;

import com.ziborov.raifproject.dto.SocksAccountingRequest;

public interface SocksWarehouseService {

    void socksIncome(SocksAccountingRequest incomeRequest);

    void socksOutcome(SocksAccountingRequest outcomeRequest);

    Long getSocksQuantity(String color, String operation, Integer cottonPart);

}