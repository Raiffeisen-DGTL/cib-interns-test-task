package ru.hlem.storesocksraif.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hlem.storesocksraif.entity.Operation;
import ru.hlem.storesocksraif.entity.Sock;
import ru.hlem.storesocksraif.repository.SockRepository;
import ru.hlem.storesocksraif.service.SockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SockServiceImpl implements SockService {
    private final SockRepository sockRepository;

    @Autowired
    public SockServiceImpl(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @Override
    public String show(String color,
                       String operation,
                       Integer cottonPart) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Argument color is incorrect!");
        }
        if (cottonPart ==null || cottonPart < 0 || cottonPart > 100) {
            throw new IllegalArgumentException("Argument cottonPart is incorrect!");
        }

        Operation op = Operation.fromText(operation).
                orElseThrow(() -> new IllegalArgumentException("Argument operation not equal moreThan, lessThan or equal!"));

        List<Sock> sockList = new ArrayList<>();
        switch (op) {
            case MORE_THAN:
                sockList = sockRepository.findAllByColorAndCottonPartIsGreaterThan(color, cottonPart);
                break;
            case EQUAL:
                sockList = sockRepository.findByColorAndCottonPart(color, cottonPart);
                break;
            case LESS_THAN:
                sockList = sockRepository.findAllByColorAndCottonPartIsLessThan(color, cottonPart);
        }
        int count = 0;
        for (Sock sock : sockList) {
            count += sock.getQuantity();
        }
        return String.valueOf(count);
    }

    @Override
    public void income(Sock sock) {
        List<Sock> existSocks = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (existSocks != null && !existSocks.isEmpty()) {
            Sock existSock = existSocks.get(0);
            existSock.setQuantity(existSock.getQuantity() + sock.getQuantity());
            sockRepository.save(existSock);
        } else {
            sockRepository.save(sock);
        }
    }

    @Override
    public void outcome(Sock sock) {
        List<Sock> existSocks = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (existSocks != null && !existSocks.isEmpty()) {
            Sock existSock = existSocks.get(0);
            if (existSock.getQuantity() > sock.getQuantity()) {
                existSock.setQuantity(existSock.getQuantity() - sock.getQuantity());
                sockRepository.save(existSock);
            } else {
                if (existSock.getQuantity() == sock.getQuantity()) {
                    sockRepository.delete(existSock);
                } else {
                    throw new IllegalArgumentException("Socks with color=" + sock.getColor() +
                            " and cottonPart=" + sock.getCottonPart() +
                            " less, than " + sock.getQuantity() + "!");
                }
            }
        } else {
            throw new IllegalArgumentException("Socks with color=" + sock.getColor() +
                    " and cottonPart=" + sock.getCottonPart() +
                    " not exists!");
        }
    }
}
