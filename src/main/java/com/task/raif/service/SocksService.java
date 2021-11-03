package com.task.raif.service;

import com.task.raif.dto.SocksDto;

public interface SocksService {

    int getSocksByParams(String color, String operation, int cottonPart);

    void income(SocksDto socksDTO);

    void outcome(SocksDto socksDTO);
}
