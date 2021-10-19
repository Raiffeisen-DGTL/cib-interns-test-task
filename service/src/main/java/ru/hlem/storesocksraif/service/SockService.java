package ru.hlem.storesocksraif.service;

import ru.hlem.storesocksraif.entity.Sock;

public interface SockService {
    String show(String color, String operation, Integer cottonPart);

    void income(Sock sock);

    void outcome(Sock sock);

}
