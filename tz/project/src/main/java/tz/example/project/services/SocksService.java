package tz.example.project.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tz.example.project.exceptions.InvalidParametrException;
import tz.example.project.models.Socks;
import tz.example.project.repositories.SocksRepository;

import java.util.Optional;

@Component
public class SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public int countOfSocks(String color, String operation, Integer cottonPart) {
        Optional<Socks> optionalSocks;
        if (cottonPart >= 0 && cottonPart <= 100) {
            switch (operation) {
                case "moreThan":
                    optionalSocks = socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
                    if (optionalSocks.isPresent()) {
                        return optionalSocks.get().getQuantity();
                    }
                    break;
                case "lessThan":
                    optionalSocks = socksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
                    if (optionalSocks.isPresent()) {
                        return optionalSocks.get().getQuantity();
                    }
                    break;
                case "equal":
                    optionalSocks = socksRepository.findByColorAndCottonPartEquals(color, cottonPart);
                    if (optionalSocks.isPresent()) {
                        return optionalSocks.get().getQuantity();
                    }
                    break;
                default:
                    throw new InvalidParametrException("You wrote an incorrect format of compare!");
            }
            throw new InvalidParametrException("You wrote an incorrect format of cottonPart!");
        }
        throw new InvalidParametrException("Parametrs don't exist!");
    }
}
