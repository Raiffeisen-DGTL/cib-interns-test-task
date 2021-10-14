package com.kurtlike.rbank.db;

import javax.transaction.Transactional;

public interface SocksService {
    @Transactional
    public long addSocks(String color, int cottonPart, int quantity);
    public long getSocks(String color, int cottonPart, int quantity);
    public long getQuantity(String color,String operation, int cottonPart);
}
