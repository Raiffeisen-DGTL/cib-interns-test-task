package ru.testJava.service;

import org.springframework.stereotype.Service;
import ru.testJava.dto.SocksDto;
import ru.testJava.entity.Socks;
import ru.testJava.exception.OutcomeImpossibleException;
import ru.testJava.repository.SocksRepository;


import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public void income(SocksDto socksDto) {
        Optional<Integer> id = socksRepository.getIdByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (id.isPresent()) {
            Socks socks = socksRepository.findById(id.get()).get();
            socks.setQuantity(socks.getQuantity() + socksDto.getQuantity());
            socksRepository.save(socks);
        } else socksRepository.save(new Socks(socksDto.getColor(), socksDto.getCottonPart(), socksDto.getQuantity()));
    }

    public void outcome(SocksDto socksDto) throws OutcomeImpossibleException {
        Optional<Integer> id = socksRepository.getIdByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (id.isPresent()) {
            Socks socks = socksRepository.findById(id.get()).get();
            if (socks.getQuantity() - socksDto.getQuantity() > 0) {
                socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
                socksRepository.save(socks);
            } else if (socks.getQuantity() - socksDto.getQuantity() == 0) socksRepository.delete(socks);
            else throw new OutcomeImpossibleException("Error, not enough product");
        } else throw new OutcomeImpossibleException("There are no such socks now");
    }

    public int getAll(String color, String operation, int cottonPart) {
        Optional<Integer> count = switch (operation) {
            case "moreThan" -> socksRepository.getSocksMoreThan(color, cottonPart);
            case "lessThan" -> socksRepository.getSocksLessThan(color, cottonPart);
            case "equal" -> socksRepository.getSocksEqual(color, cottonPart);
            default -> throw new ValidationException("Invalid value operation: " + operation);
        };
        return count.orElse(0);
    }

}
