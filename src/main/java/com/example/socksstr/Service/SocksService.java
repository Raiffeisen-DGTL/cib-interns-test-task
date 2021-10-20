package com.example.socksstr.Service;

import com.example.socksstr.Model.Socks;


public interface SocksService {

    void socksIncome(Socks socks);

    void socksOutcome(Socks socks);

    long getSocksQuantity(String color, String operation, long cottonPart);


}

