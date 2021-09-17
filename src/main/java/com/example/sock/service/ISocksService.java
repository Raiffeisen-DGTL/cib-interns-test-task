package com.example.sock.service;

import com.example.sock.domain.Socks;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface ISocksService {
    Optional<Socks> getByColorAndCottonPart(String color, String operation, int cottonPart);
    void addSocks(Socks socks);
    void reduceSocks(Socks socks);
}
