package com.example.socksmanager.db.socks.service;

import com.example.socksmanager.model.exceprion.NotEnoughSocks;

public interface SocksService {
    void incomeSocks(String color, int cottonPart, int quantity);
    void outcomeSocks(String color, int cottonPart, int quantity) throws NotEnoughSocks;
    int getSocksCount(String color, String operation, int cottonPart);
}
