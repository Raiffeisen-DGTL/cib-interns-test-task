package com.socks.demo.service;

import com.socks.demo.exception.IncorrectCottonPartException;
import com.socks.demo.exception.IncorrectParametersException;
import com.socks.demo.exception.IncorrectQuantityException;
import com.socks.demo.exception.NotEnoughSocksException;
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
        String color = sock.getColor();
        Integer cottonPart = sock.getCottonPart();
        Integer quantity = sock.getQuantity();

        if(cottonPart <= 0 || cottonPart > 100) {
            throw new IncorrectCottonPartException();
        }
        if(quantity <= 0) {
            throw new IncorrectQuantityException();
        }

        Sock newSock = sockRepo.findSockByColorAndCottonPart(color, cottonPart);

        if (newSock == null) {
            sockRepo.save(sock);
        } else {
            sockRepo.incomeSocks(color, quantity, cottonPart);
        }
    }

    @Override
    @Transactional
    public void deleteSocks(Sock sock) {
        String color = sock.getColor();
        Integer cottonPart = sock.getCottonPart();
        Integer quantity = sock.getQuantity();

        if(cottonPart <= 0 || cottonPart > 100) {
            throw new IncorrectCottonPartException();
        }
        if(quantity <= 0) {
            throw new IncorrectQuantityException();
        }

        Sock newSock = sockRepo.findSockByColorAndCottonPart(color, cottonPart);

        if (newSock.getQuantity() < sock.getQuantity()) {
            throw new NotEnoughSocksException(newSock.getQuantity());
        } else {
            sockRepo.outcomeSocks(color, quantity, cottonPart);
        }

    }

    @Override
    public Integer amountSocks(String color, String operation, Integer cottonPart) throws IncorrectParametersException {
        if(cottonPart <= 0 || cottonPart > 100) {
            throw new IncorrectCottonPartException();
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

    private List<Sock> sortSocks(String color, String operation, Integer cottonPart) throws IncorrectParametersException {
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
