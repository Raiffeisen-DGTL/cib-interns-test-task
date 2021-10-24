package ru.yakovlev.socks.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovlev.socks.model.Sock;
import ru.yakovlev.socks.repository.SockRepo;

import java.util.List;


@Service
public class SockService {
    private final SockRepo sockRepo;

    @Autowired
    public SockService(SockRepo sockRepo) {
        this.sockRepo = sockRepo;
    }

    @Transactional
    public int getSocks(String color, String operation, int cottonPart) {
        int num = 0;
        List<Sock> socks = sockRepo.findByColor(color);
        if (socks.isEmpty()) return num;
        switch (operation) {
            case "moreThan":
                for (Sock sock : socks) {
                    if (sock.getCottonPart() > cottonPart) num += sock.getQuantity();
                }
                break;
            case "lessThan":
                for (Sock sock : socks) {
                    if (sock.getCottonPart() < cottonPart) num += sock.getQuantity();
                }
                break;
            case "equals":
                for (Sock sock : socks) {
                    if (sock.getCottonPart() == cottonPart) num += sock.getQuantity();
                }
                break;
            default:
                throw new IllegalArgumentException("Incorrect operation, you must choose moreThan, lessThan or equals");
        }
        return num;
    }

    @Transactional
    public void income(Sock sock) {
        if (sockRepo.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).isEmpty()) {
            sockRepo.save(sock);
        } else {
            Sock socks = sockRepo.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).get();
            socks.setQuantity(sock.getQuantity() + socks.getQuantity());
            sockRepo.save(socks);
        }
    }


    @Transactional
    public void outcome(Sock sock) {
        if (sockRepo.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).isPresent()) {
            Sock socks = sockRepo.findByColorAndCottonPartEquals(sock.getColor(), sock.getCottonPart()).get();
            socks.setQuantity(sock.getQuantity() - socks.getQuantity());
            sockRepo.save(socks);
        }
    }
}
