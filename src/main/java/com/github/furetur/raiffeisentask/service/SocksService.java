package com.github.furetur.raiffeisentask.service;

import com.github.furetur.raiffeisentask.restData.OperationType;

public interface SocksService {
    int countSocks(String color, OperationType operation, int cottonPart);
    void addSocks(String color, int cottonPart, int quantity);
    void removeSocks(String color, int cottonPart, int quantity);
}
