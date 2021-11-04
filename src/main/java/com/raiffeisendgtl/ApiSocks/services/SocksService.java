package com.raiffeisendgtl.ApiSocks.services;

import com.raiffeisendgtl.ApiSocks.entities.Socks;

public interface SocksService {

    void income(Socks socks);

    void outcome(Socks socks);

    Integer getCountSocks(String color, String operation, int cottonPart);

}
