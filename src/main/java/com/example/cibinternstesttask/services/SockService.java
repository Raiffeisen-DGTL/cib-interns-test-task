package com.example.cibinternstesttask.services;

import com.example.cibinternstesttask.io.entities.SockEntity;
import com.example.cibinternstesttask.ui.model.requests.SocksDetailsRequest;
import com.example.cibinternstesttask.ui.model.responses.SocksOperations;

public interface SockService {
    SockEntity incomeSocks(SocksDetailsRequest socksDetailsRequest);
    SockEntity outcomeSocks(SocksDetailsRequest socksDetailsRequest);
    Long getSocksQuantity(String color, SocksOperations operation, int cottonPart);
}
