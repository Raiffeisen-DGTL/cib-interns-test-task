package com.github.matveyakulov.raifweb.dao;

import com.github.matveyakulov.raifweb.entity.Sock;
import com.github.matveyakulov.raifweb.enums.Operation;

import java.util.List;


public interface SockDAO{

    void addSock(Sock sock);

    void deleteSock(Sock sock);

    int getAll(String color, Operation operation, int cottonPart);

}
