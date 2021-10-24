package ru.tshtk.accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tshtk.accounting.dto.SocksRequest;
import ru.tshtk.accounting.entity.Socks;
import ru.tshtk.accounting.exception.OutcomeImpossibleException;
import ru.tshtk.accounting.repository.SocksRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public void registerIncome(SocksRequest socksIn) {
        Optional<Integer> id = socksRepository.getIdByColorAndCottonPart(socksIn.getColor(), socksIn.getCottonPart());
        if (id.isPresent()) {
            Socks socksBalance = socksRepository.findById(id.get()).get();
            socksBalance.setQuantity(socksBalance.getQuantity() + socksIn.getQuantity());
            socksRepository.save(socksBalance);
        }
        else socksRepository.save(new Socks(socksIn.getColor(), socksIn.getCottonPart(), socksIn.getQuantity()));
    }

    public void registerOutcome(SocksRequest socksOut) throws OutcomeImpossibleException {
        Optional<Integer> id = socksRepository.getIdByColorAndCottonPart(socksOut.getColor(), socksOut.getCottonPart());
        if (id.isPresent()) {
            Socks socksBalance = socksRepository.findById(id.get()).get();
            if (socksBalance.getQuantity() - socksOut.getQuantity() > 0) {
                socksBalance.setQuantity(socksBalance.getQuantity() - socksOut.getQuantity());
                socksRepository.save(socksBalance);
            }
            else if (socksBalance.getQuantity() - socksOut.getQuantity() == 0) socksRepository.delete(socksBalance);
            else throw new OutcomeImpossibleException("На складе недостаточно носков для отпуска требуемого количества");
        }
        else throw new OutcomeImpossibleException("Запрашиваемые носки отсутствуют на складе");
    }

    public int countQuantity (String requestColor, String operation, int requestCottonPart) {
        Optional<Integer> count;
        switch (operation) {
            case "moreThan" :
                count = socksRepository.getSocksMoreThan(requestColor, requestCottonPart); break;
            case "lessThan" :
                count = socksRepository.getSocksLessThan(requestColor, requestCottonPart); break;
            case "equal" :
                count = socksRepository.getSocksEqual(requestColor, requestCottonPart); break;
            default:
                throw new ValidationException("Недопустимое значение operation: " + operation);
        }
        return count.isPresent() ? count.get() : 0;
    }
}
