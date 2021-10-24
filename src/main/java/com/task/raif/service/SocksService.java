package com.task.raif.service;

import com.task.raif.model.Socks;

public interface SocksService {

    int getSocksByParams(String color, String operation, int cottonPart);

    void income(Socks socks);

    void outcome(Socks socks);
}
