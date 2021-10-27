package ru.raiffeisen.socksapp.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.raiffeisen.socksapp.Exception.IncorrectParametersException;
import ru.raiffeisen.socksapp.Model.Socks;
import ru.raiffeisen.socksapp.Repository.SocksRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Override
    public Long getQuantityOfSocks(String color, String operation, int cottonPart) {
        List<Socks> socksList = new ArrayList<>();
        switch (operation) {
            case "moreThan":
                socksList = socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case "lessThan":
                socksList = socksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case "equal":
                socksList.add(socksRepository.findByColorAndCottonPart(color, cottonPart));
                break;
            default:
                throw new IncorrectParametersException("Operation error", "operation: " + operation);
        }
        Long quantity = socksList.stream()
                .map(socks -> socks.getQuantity())
                .collect(Collectors.summingLong(Long::longValue));

        return quantity;
    }

    @Override
    @Transactional
    public void addSocks(Socks socks) {
        Socks socksFromDb = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksFromDb != null) {
            socksFromDb.setQuantity(socksFromDb.getQuantity() + socks.getQuantity());
            socksRepository.save(socksFromDb);
        }
        else {
            socksRepository.save(socks);
        }
    }

    @Override
    @Transactional
    public void removeSocks(Socks socks) {
        Socks socksFromDb = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksFromDb == null) {
            throw new IncorrectParametersException("Данных носок нет на складе", socks.toString());
        }
        Long currentQuantity = socksFromDb.getQuantity() - socks.getQuantity();
        if (currentQuantity < 0) {
            throw new IncorrectParametersException("На складе находится меньшее количество носок", "quantity: " + socks.getQuantity());
        }
        else if (currentQuantity == 0) {
            socksRepository.delete(socksFromDb);
        }
        else {
            socksFromDb.setQuantity(currentQuantity);
            socksRepository.save(socksFromDb);
        }
    }
}
