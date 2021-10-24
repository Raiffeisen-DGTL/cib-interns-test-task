package com.socks.demo.service;

import com.socks.demo.exception.IncorrectParametersException;
import com.socks.demo.model.Sock;

public interface SockService {

    void addSocks(Sock sock);

    void deleteSocks(Sock sock);

    Integer amountSocks(String color, String operation, Integer cottonPart);

}
