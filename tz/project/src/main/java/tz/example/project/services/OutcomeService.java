package tz.example.project.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tz.example.project.exceptions.InvalidParametrException;
import tz.example.project.models.Socks;
import tz.example.project.repositories.SocksRepository;

import java.util.Optional;

@Component
public class OutcomeService {

    @Autowired
    private final SocksRepository socksRepository;

    public OutcomeService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }
    public void outcome(@NonNull Socks socks) {
        if (socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100 && socks.getQuantity() > 0) {
            Optional<Socks> socks1 = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
            if (socks1.isPresent()) {
                Socks findSocks = socks1.get();
                findSocks.setQuantity(findSocks.getQuantity() - socks.getQuantity());
            }
        }
        else
            throw new InvalidParametrException("you wrote an incorrect format!");
    }
}
