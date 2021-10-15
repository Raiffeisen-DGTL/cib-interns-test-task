package com.example.demo.Service;

import com.example.demo.model.request.CreateSockRequest;

//import com.sun.istack.NotNull;

public interface SockService {
    void registerSockIncome(CreateSockRequest request);
    boolean registerSockOutcome(CreateSockRequest request);
    Long getSockQuantity(String color, String operation, Integer cottonPart);
}


