package ru.dnsk.accountingofsocks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.dnsk.accountingofsocks.model.PairOfSocks;
import ru.dnsk.accountingofsocks.repository.PairOfSocksRepository;

@Service
@Transactional(readOnly = true)
public class PairOfSocksService {
    private final PairOfSocksRepository pairOfSocksRepository;

    @Autowired
    public PairOfSocksService(PairOfSocksRepository pairOfSocksRepository) {
        this.pairOfSocksRepository = pairOfSocksRepository;
    }

    @Transactional
    public PairOfSocks changeQuantity(String color, int cottonPart, int quantity) {
        PairOfSocks pairOfSocks = pairOfSocksRepository.findByColorAndCottonPart(color, cottonPart);

        if (pairOfSocks == null) {
            return pairOfSocksRepository.save(new PairOfSocks(color, cottonPart, quantity));
        }

        int changingQuantity = pairOfSocks.getQuantity() + quantity;

        if (changingQuantity == 0) {
            pairOfSocksRepository.delete(pairOfSocks);
            return null;
        } else if (changingQuantity < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        pairOfSocks.setQuantity(changingQuantity);
        return pairOfSocksRepository.save(pairOfSocks);
    }

    public String getQuantityByColorAndCottonPart(String color, String operation, int cottonPart) {
        PairOfSocks pairOfSocks = switch (operation) {
            case "equal" -> pairOfSocksRepository.findByColorAndCottonPart(color, cottonPart);
            case "lessThan" -> pairOfSocksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
            case "moreThan" -> pairOfSocksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        };
        return pairOfSocks != null ? String.valueOf(pairOfSocks.getQuantity()) : "0";
    }
}
