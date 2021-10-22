package com.github.matveyakulov.raifweb.service;

import com.github.matveyakulov.raifweb.entity.Sock;
import com.github.matveyakulov.raifweb.enums.Operation;

public interface SockService {

    void income(Sock sock);

    void outcome(Sock sock);

    int getAll(String color, Operation operation, int cottonPart);
}
