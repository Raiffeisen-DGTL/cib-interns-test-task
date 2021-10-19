package com.example.socksservice.service;

import com.example.socksservice.dto.CountOperation;
import com.example.socksservice.entity.Sock;

import java.util.Optional;

public interface SocksService {
    Optional<Sock> getByColorAndCotton(String color, int cotton);

    Sock save(Sock sock);

    Sock increaseQuantity(Sock sock, int qty);

    Sock decreaseQuantity(Sock sock, int qty);

    Long getSocksCount(String color, int cotton, CountOperation operation);
}
