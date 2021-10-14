package com.example.socksmanager.db.socks.dao;

public interface SocksDao {
    void insertSocks(String color, int cottonPart, int quantity);
    void incomeSocks(String color, int cottonPart, int quantity);
    void outcomeSocks(String color, int cottonPart, int quantity);
    int getMoreCount(String color, int cottonPart);
    int getEqualCount(String color, int cottonPart);
    int getLessCount(String color, int cottonPart);
}
