package ru.prostokventin.socks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public Socks save(Socks socks) {
        Optional<Socks> foundSocks = socksRepository.findById(
                new SocksId(socks.getColor(), socks.getCottonPart())
        );
        if (foundSocks.isPresent()) {
            socks.setQuantity(socks.getQuantity() + foundSocks.get().getQuantity());
        }
        return socksRepository.save(socks);
    }

    public Socks income(Socks incomeSocks) {
        return socksRepository.findById(new SocksId(incomeSocks.getColor(), incomeSocks.getCottonPart()))
            .map(socks -> {
                socks.increaseQuantity(incomeSocks.getQuantity());
                return socksRepository.save(socks);
            })
            .orElseGet(() -> {
                return socksRepository.save(incomeSocks);
            });
    }

    public Socks outcome(Socks outcomeSocks) {
        return socksRepository.findById(new SocksId(outcomeSocks.getColor(), outcomeSocks.getCottonPart()))
                .map(socks -> {
                    if (socks.getQuantity() < outcomeSocks.getQuantity()) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Недостаточно носков на складе"
                        );
                    }
                    socks.decreaseQuantity(outcomeSocks.getQuantity());
                    return socksRepository.save(socks);
                })
                .orElseGet(() -> {
                    return socksRepository.save(outcomeSocks);
                });
    }

    public int getSocksCount(String color, Operation operation, int cottonPart) {

        switch(operation) {
            case lessThan: return socksRepository.totalQuantityByColorCottonPartLessThan(color, cottonPart);
            case moreThan: return socksRepository.totalQuantityByColorCottonPartGreaterThan(color, cottonPart);
            case equal: return socksRepository.totalQuantityByColorCottonPartEquals(color, cottonPart);
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

}
