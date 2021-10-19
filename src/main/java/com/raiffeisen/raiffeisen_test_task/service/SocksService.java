package com.raiffeisen.raiffeisen_test_task.service;

import com.raiffeisen.raiffeisen_test_task.model.Operation;
import com.raiffeisen.raiffeisen_test_task.model.Socks;

public interface SocksService {
    void addSocks(Socks socks);

    void decreaseSocks(Socks socks);

    Socks getSocks(String color, Operation operation, byte cottonPart);
}
