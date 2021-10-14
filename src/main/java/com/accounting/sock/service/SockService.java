package com.accounting.sock.service;

import com.accounting.sock.entity.Sock;

public interface SockService {

    void registerSockIncome(Sock sock);
    boolean registerSockOutcome(Sock sock);
    long getTotalSockQuantity(String color, String operation, Integer cottonPart);

}
