package com.raif.socks.service;

import com.raif.socks.dto.SocksDto;

public interface SocksService {

    boolean income(SocksDto socksDto);
    boolean outcome(SocksDto socksDto);
    int find(String color, Operation operation, int cottonPart);

}
