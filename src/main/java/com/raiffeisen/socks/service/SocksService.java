package com.raiffeisen.socks.service;

import com.raiffeisen.socks.dto.SockDto;

public interface SocksService {
    void registerSocks(SockDto sockDto);

    void outcomeSocks(SockDto sockDto);

    SockDto getSocksByParams(String color, String operation, Integer cottonPart);
}
