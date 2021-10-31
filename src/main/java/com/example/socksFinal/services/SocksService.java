package com.example.socksFinal.services;

import com.example.socksFinal.model.SocksApp;

import java.util.List;

public interface SocksService {

    List<SocksApp> getSocks();

    void insert(List<SocksApp> socks);

    boolean deleteSocks(List<SocksApp> socks);

    int countSocksWithCottonMore (String color, int num);

    int countSocksWithCottonLess (String color, int num);

    int countSocksBy (String color, int num);

}
