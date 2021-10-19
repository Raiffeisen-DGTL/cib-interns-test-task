package com.raifaizen.storage.service;

import com.raifaizen.storage.exceptions.RequestException;
import com.raifaizen.storage.exceptions.StorageException;
import com.raifaizen.storage.models.Operation;
import com.raifaizen.storage.models.Sock;
import com.raifaizen.storage.repository.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class SockService {

    @Autowired
    private SockRepository sockRepository;

    public List<Sock> getSocks(String color, String operationInRequest, Integer cottonPart) throws RuntimeException {

        Operation operation;
        try {
            operation = operationInRequest == null ?
                    Operation.valueOf("equal") : Operation.valueOf(operationInRequest);

        } catch (IllegalArgumentException e) {
            throw new RequestException("There is no such operation");
        }

        boolean cottonPartIsEmpty = cottonPart == null;
        boolean colorIsEmpty = color.isEmpty();

        return cottonPartIsEmpty && colorIsEmpty ? sockRepository.findAll() :
               !cottonPartIsEmpty && !colorIsEmpty ? getSocksByColorAndCottonPart(color, operation, cottonPart) :
               !cottonPartIsEmpty ? getSocksByCottonPart(operation, cottonPart) :
               sockRepository.findByColor(color);
    }

    public void income(String color, Integer cottonPart, Integer quantity) throws RuntimeException {
        List<Sock> sockInList = getSocks(color, "equal", cottonPart);
        Sock sock;

        if (!sockInList.isEmpty()) {
            sock = sockInList.get(0);
            sock.setQuantity(sock.getQuantity() + quantity);
        } else {
            sock = new Sock(color, cottonPart, quantity);
        }

        try {
            sockRepository.save(sock);
        } catch (ConstraintViolationException e) {
            throw new StorageException("Validation");
        }
    }

    public void outcome(String color, Integer cottonPart, Integer quantity) throws RuntimeException {
        List<Sock> sockInList = getSocks(color, "equal", cottonPart);
        Sock sock;

        if (!sockInList.isEmpty()) {
            sock = sockInList.get(0);
            int newQuantity = sock.getQuantity() - quantity;

            if (newQuantity < 0) {
                throw new StorageException("Not enough socks");
            }

            if (newQuantity == 0) {
                sockRepository.delete(sock);

            } else {
                sock.setQuantity(newQuantity);
                sockRepository.save(sock);
            }

        } else {
            throw new StorageException("These socks are not in stock");
        }
    }

    private List<Sock> getSocksByColorAndCottonPart(String color, Operation operation, Integer cottonPart) throws RuntimeException {
        switch (operation) {
            case equal:
                return sockRepository.findByColorAndCottonPartEqual(color, cottonPart);
            case lessThan:
                return sockRepository.findByColorAndCottonPartLess(color, cottonPart);
            case moreThan:
                return sockRepository.findByColorAndCottonPartMore(color, cottonPart);
        }
        throw new RuntimeException("Something went wrong");
    }

    private List<Sock> getSocksByCottonPart(Operation operation, Integer cottonPart) throws RuntimeException {
        switch (operation) {
            case equal:
                return sockRepository.findByCottonPartEqual(cottonPart);
            case lessThan:
                return sockRepository.findByCottonPartLess(cottonPart);
            case moreThan:
                return sockRepository.findByCottonPartMore(cottonPart);
        }
        throw new RuntimeException("Something went wrong");
    }
}
