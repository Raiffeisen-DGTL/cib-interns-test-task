package com.example.interntask;


public interface SocksService {
    void income(SocksByType socks);

    void outcome(SocksByType socks);

    Integer getNumberSocks(String color, String operation, Integer cottonPart);
}
