package com.example.socks.service;

import com.example.socks.model.Socks;
import com.example.socks.utils.Operation;

public interface SocksService {

    void addSocks(Socks socks);
    void deleteSocks(Socks socks) throws Exception;
    int getSocks(String color, Operation operation, int cottonPart);
}
