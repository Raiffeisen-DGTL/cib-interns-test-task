package com.github.matveyakulov.raifweb.service;

import com.github.matveyakulov.raifweb.dao.SockDAO;
import com.github.matveyakulov.raifweb.entity.Sock;
import com.github.matveyakulov.raifweb.enums.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SockServiceImpl implements SockService{

    @Autowired
    private SockDAO sockDAO;

    @Override
    public void income(Sock sock) {

        sockDAO.addSock(sock);
    }

    @Override
    public void outcome(Sock sock) {
        sockDAO.deleteSock(sock);
    }

    @Override
    public int getAll(String color, Operation operation, int cottonPart) {
        return sockDAO.getAll(color, operation, cottonPart);
    }

}
