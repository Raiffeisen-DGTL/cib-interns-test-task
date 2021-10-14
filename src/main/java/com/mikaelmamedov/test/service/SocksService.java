package com.mikaelmamedov.test.service;

import com.mikaelmamedov.test.model.Socks;
import com.mikaelmamedov.test.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public List<Socks> findAll() {
        return socksRepository.findAll();
    }

    public Optional<Socks> findByColorAndCottonPart(String color, int cottonPart) {
        return socksRepository.findByColorAndCottonPart(color, cottonPart);
    }

    public Socks updateSocksByQuantity(Socks socks, int quantity) {
        socks.setQuantity(socks.getQuantity() + quantity);
        return socksRepository.save(socks);
    }

    public Socks save(Socks socks) {
        return socksRepository.save(socks);
    }

    public Socks takeSocksFromStock(Socks socks, int quantity) {
        if(socks.getQuantity() < quantity){
            socks.setQuantity(0);
            return socksRepository.save(socks);
        }

        socks.setQuantity(socks.getQuantity() - quantity);
        return socksRepository.save(socks);
    }

    public Optional<List<Socks>> findByParameter(String color, int cottonPart, String criteria) {
        switch(criteria) {
            case "moreThan":
                return socksRepository.findAllByColorAndCottonPartIsGreaterThan(color, cottonPart);
            case "lessThan":
                return socksRepository.findAllByColorAndCottonPartIsLessThan(color, cottonPart);
            case "equal":
                return socksRepository.findAllByColorAndCottonPartEquals(color, cottonPart);
            default:
                return Optional.empty();
        }
    }
}
