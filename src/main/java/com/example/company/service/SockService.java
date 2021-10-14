package com.example.company.service;

import com.example.company.entity.Operation;
import com.example.company.entity.Sock;
import lombok.NonNull;

public interface SockService {
@NonNull Sock getSockByColorAndCottonPart(@NonNull String color, @NonNull Operation operation,@NonNull int cottonPart);
void addSocks(@NonNull Sock sock);
void reduceSocks(@NonNull Sock sock);
}
