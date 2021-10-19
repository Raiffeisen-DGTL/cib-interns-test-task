package com.kozlov.springjpa.spring_data_jpa.service;


import com.kozlov.springjpa.spring_data_jpa.entity.Socks;

public interface SocksService {

    void setIncomeSocks(Socks socks);

    void setOutcomeSocks(Socks socks);

    int getSocksQuantity(String color, String operation, int cottonPart);
}
