package alaev.dev.raiffeisentesttask.service;

import alaev.dev.raiffeisentesttask.domain.Sock;
import alaev.dev.raiffeisentesttask.repository.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SockService {

    private final SockRepository repository;

    @Autowired
    public SockService(SockRepository repository) {
        this.repository = repository;
    }

    public void addSock(String color, Integer cottonPart, Integer quantity) {
        if (repository.existsByColorAndCottonPart(color, cottonPart)) {
            Sock sockByColor = repository.getSockByColorAndCottonPart(color, cottonPart);
            sockByColor.setQuantity(sockByColor.getQuantity() + quantity);
            repository.save(sockByColor);
        } else {
            repository.save(new Sock(null, color, cottonPart, quantity));
        }
    }
}
