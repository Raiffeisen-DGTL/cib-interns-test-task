package com.example.sock.service;

import com.example.sock.domain.Socks;
import com.example.sock.enums.Operations;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface ISocksService {
    @NonNull
    Socks getByColorAndCottonPart(@NonNull String color, @NonNull Operations operation, @NonNull Long cottonPart);
    void addSocks(@NonNull Socks socks);
    void reduceSocks(@NonNull Socks socks);
}
