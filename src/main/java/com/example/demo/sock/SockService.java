package com.example.demo.sock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SockService {

    private final SockRepository sockRepository;

    @Autowired
    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public Optional<String> getSocks(String color, String operation, Byte cottonPart) {
        switch (operation) {
            case "moreThan":
                return Optional.of(sockRepository.findByColorAndCottonPartGreaterThan(color, cottonPart).get().getQuantity().toString());
            case "lessThan":
                return Optional.of(sockRepository.findByColorAndCottonPartLessThan(color, cottonPart).get().getQuantity().toString());
            default:
                return Optional.of(sockRepository.findByColorAndCottonPart(color, cottonPart).get().getQuantity().toString());
        }
    }

    public void modifySock(Sock sock, String uri) {
        Short quantity;
        Optional<Sock> sockByColor = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        System.out.println(uri);
        if (sockByColor.isPresent()) {
            if (uri.equals("/api/socks/outcome")) {
                quantity = (short)(sockByColor.get().getQuantity() - Short.valueOf(sock.getQuantity()));
            } else {
                quantity = (short)(sockByColor.get().getQuantity() + Short.valueOf(sock.getQuantity()));
            }
            //throw new IllegalStateException("color taken");
            sockRepository.setQuantity((short)(quantity),sockByColor.get().getId());
        } else {
            sockRepository.save(sock);
        }
    }
}
