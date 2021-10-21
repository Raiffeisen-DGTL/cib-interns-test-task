package ru.javabootcamp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javabootcamp.dto.SocksRqDto;
import ru.javabootcamp.model.Socks;
import ru.javabootcamp.repository.SocksRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class SocksService {

    private final SocksRepository repository;

    public void addIncome(SocksRqDto socks) {
        Socks newSocks;
        List<Socks> foundSocks = findSocksByParams(socks.getColor(), socks.getCottonPart());

        if (foundSocks.isEmpty()) {
            newSocks = new Socks();
            newSocks.setColor(socks.getColor());
            newSocks.setCottonPart(socks.getCottonPart());
            newSocks.setQuantity(socks.getQuantity());
        } else {
            newSocks = foundSocks.get(0);
            newSocks.setQuantity(newSocks.getQuantity() + socks.getQuantity());
        }

        repository.save(newSocks);
    }

    public void addOutcome(SocksRqDto socks) {
        List<Socks> foundSocks = findSocksByParams(socks.getColor(), socks.getCottonPart());

        if (foundSocks.isEmpty() || (foundSocks.get(0).getQuantity() < socks.getQuantity())) {
            throw new ConstraintViolationException(String.format("socks no such socks or quantity"),
                    new HashSet<ConstraintViolation<?>>());
        } else {
            Socks updatedSocks = foundSocks.get(0);
            updatedSocks.setQuantity(updatedSocks.getQuantity() - socks.getQuantity());
            repository.save(updatedSocks);
        }
    }

    public String getStocks(String color, String operation, int cottonPart) {
        return Integer.toString(getSocksQuantity(color, operation, cottonPart));
    }


    private List<Socks> findSocksByParams(String color, int cottonPart) {
        return repository.findAllByColorAndCottonPart(color, cottonPart);
    }

    private int getSocksQuantity(String color, String operation, int cottonPart) {
        List<Socks> socks;

        switch (operation) {
            case "moreThan":
                socks = repository.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case "lessThan":
                socks = repository.findAllByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case "equal":
                socks = repository.findAllByColorAndCottonPartEquals(color, cottonPart);
                break;
            default:
                throw new ConstraintViolationException(String.format("operation unrecognized operation " + operation),
                        new HashSet<ConstraintViolation<?>>());
        }

        int socksQuantity = 0;
        for (Socks s : socks)
            socksQuantity += s.getQuantity();

        return socksQuantity;
    }
}
