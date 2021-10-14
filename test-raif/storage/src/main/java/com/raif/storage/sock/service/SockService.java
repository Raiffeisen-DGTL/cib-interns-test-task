package com.raif.storage.sock.service;

import com.raif.storage.sock.model.SockDto;

public interface SockService {

    void createSockIncome(SockDto sock);

    void createSockOutcome(SockDto sock);

    long countSocks(String color, String operation, int cottonPart);
}
