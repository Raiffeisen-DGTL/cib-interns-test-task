package com.raiffeisen.service;

import com.raiffeisen.model.Sock;
import com.raiffeisen.repository.SockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SockService {

    private final SockRepository sockRepository;

    public SockService (SockRepository sockRepository) { this.sockRepository = sockRepository; }

    public void save(Sock sock) {
        sockRepository.save(sock);
    }

    public void update(Sock sock) {
        sockRepository.update(sock);
    }

    public int findByCriteria(List<String> list) {
        switch (list.get(1)) {
            case ("moreThan") -> {
                return findWhereCottonPartBigger(list.get(0), Integer.parseInt(list.get(2)));
            }
            case ("lessThan") -> {
                return findWhereCottonPartSmaller(list.get(0), Integer.parseInt(list.get(2)));
            }
            case ("equal") -> {
                return findWhereCottonPartEqual(list.get(0), Integer.parseInt(list.get(2)));
            }
            default -> {
                return 400;
            }
        }
    }

    public int findWhereCottonPartBigger(String color, int cottonPart) {
        return sockRepository.findWhereCottonPartBigger(color, cottonPart);
    }

    public int findWhereCottonPartSmaller(String color, int cottonPart) {
        return sockRepository.findWhereCottonPartSmaller(color, cottonPart);
    }

    public int findWhereCottonPartEqual(String color, int cottonPart) {
        return sockRepository.findWhereCottonPartEqual(color, cottonPart);
    }
}
