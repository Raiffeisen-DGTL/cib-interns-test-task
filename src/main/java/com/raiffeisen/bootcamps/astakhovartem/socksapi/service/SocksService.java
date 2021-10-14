package com.raiffeisen.bootcamps.astakhovartem.socksapi.service;


import com.raiffeisen.bootcamps.astakhovartem.socksapi.entity.Socks;

public interface SocksService {


    void increaseSocks(Socks socks);

    void decreaseSocks(Socks socks);

    int getQuantity(String color, String operation, int cottonPart);


}