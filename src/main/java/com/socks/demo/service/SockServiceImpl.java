package com.socks.demo.service;

import com.socks.demo.exception.*;
import com.socks.demo.model.Sock;
import com.socks.demo.repository.SockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SockServiceImpl implements SockService {

    private final SockRepo sockRepo;


    @Override
    @Transactional
    public void addSocks(Sock sock) {

        if(sock.getCottonPart() <= 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0 || sock.getColor().isEmpty()) {
            throw new IncorrectDataException();
        }

        Sock newSock = sockRepo.findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart());

        if (newSock == null) {
            sockRepo.save(sock);
        } else {
            sockRepo.incomeSocks(sock.getColor(), sock.getQuantity(), sock.getCottonPart());
        }
    }

    @Override
    @Transactional
    public void deleteSocks(Sock sock) {

        if(sock.getCottonPart() <= 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0 || sock.getColor().isEmpty()) {
            throw new IncorrectDataException();
        }

        Sock newSock = sockRepo.findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart());

        if (newSock.getQuantity() < sock.getQuantity()) {
            throw new NotEnoughSocksException(newSock.getQuantity());
        } else {
            sockRepo.outcomeSocks(sock.getColor(), sock.getQuantity(), sock.getCottonPart());
        }

    }

    @Override
    public Integer amountSocks(String color, String operation, Integer cottonPart) {
        if(cottonPart <= 0 || cottonPart > 100 || color.isEmpty()) {
            throw new IncorrectDataException();
        }

        List<Sock> sockList = sortSocks(color, operation, cottonPart);
        List<Integer> socksAmount = new ArrayList<>();
        Integer sumSock = 0;

        for (Sock sock : sockList) {
            socksAmount.add(sock.getQuantity());
        }

        for (Integer integer : socksAmount) {
            sumSock += integer;
        }

        return sumSock;
    }

    private List<Sock> sortSocks(String color, String operation, Integer cottonPart) {
        switch (operation) {
            case "moreThan":
                return sockRepo.calcOperationMore(color, cottonPart);
            case "lessThan":
                return sockRepo.calcOperationLess(color, cottonPart);
            case "equals":
                return sockRepo.calcOperationEquals(color, cottonPart);
            default:
                throw new IncorrectParametersException();
        }
    }

}
