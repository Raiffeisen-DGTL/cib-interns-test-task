package com.fedoseeva.socksaccountingapp.dao;

import com.fedoseeva.socksaccountingapp.models.Socks;

public interface SocksDAO {
    void income(Socks socks);

    void outcome(Socks socks);

    int select(String color, String operation, int cottonPart);

}
