package com.raiffeisen.task.service;

import com.raiffeisen.task.domain.Sock;
import com.raiffeisen.task.exception.ValidationException;
import com.raiffeisen.task.repository.SockRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class SockService {
    private final SockRepository repository;

    public SockService(SockRepository repository) {
        this.repository = repository;
    }

    public void addSocks(String color, Integer cottonPart, Integer quantity){
        Optional<Sock> requestAnswer = repository.findSockByColorAndCottonPart(color, cottonPart);
        if(requestAnswer.isPresent()) {
            requestAnswer.get().setQuantity(requestAnswer.get().getQuantity() + quantity);
            repository.save(requestAnswer.get());
        } else {
            repository.save(new Sock(null, color, cottonPart, quantity));
        }
    }

    public void removeSocks(String color, Integer cottonPart, Integer quantity){
        Optional<Sock> requestAnswer = repository.findSockByColorAndCottonPart(color, cottonPart);
        if(requestAnswer.isPresent()) {
            Sock sock = requestAnswer.get();
            if (sock.getQuantity() - quantity >= 0) {
                sock.setQuantity(sock.getQuantity() - quantity);
                repository.save(sock);
            }
                else {
                throw new  ValidationException("Warning! An attempt to write off more socks (" + quantity +
                        ") than there are in stock (" + sock.getQuantity() + ")");
            }

        } else {
            throw new  ValidationException("Warning!Such socks were not found!");
        }
    }

    public String getTotalSocksByParam(String color, Integer cottonPart, String operation) {
        Integer total;
        switch (operation){
            case ("moreThan"):
                total = repository.getTotalQuantityMore(color, cottonPart);
                break;
            case ("lessThan"):
                total = repository.getTotalQuantityLess(color, cottonPart);
                break;
            case ("equal"):
                total = repository.getTotalQuantityEquals(color, cottonPart);
                break;
            default:
                throw new ValidationException("Warning! Invalid operation!");
        }
        return total == null ? "0" : total.toString();
    }
}
