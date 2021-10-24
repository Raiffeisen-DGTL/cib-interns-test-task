package ru.raif.socks.service;

import org.springframework.stereotype.Service;
import ru.raif.socks.api.SocksService;
import ru.raif.socks.exception.BadSocksException;
import ru.raif.socks.exception.NoSuchOperationException;
import ru.raif.socks.exception.NoSuchSocksQuantityException;
import ru.raif.socks.exception.SocksNotFoundException;
import ru.raif.socks.model.SocksModel;
import ru.raif.socks.repository.SocksRepository;

import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    @Override
    public void income(SocksModel socks) {
        validate(socks);
        SocksModel socksFromTable = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksFromTable != null) {
            socksFromTable.setQuantity(socksFromTable.getQuantity() + socks.getQuantity());
            socksFromTable.setCottonPart(socks.getCottonPart());
            socksRepository.save(socksFromTable);
        } else {
            socksRepository.save(socks);
        }
    }

    @Override
    public boolean outcome(SocksModel socks) {
        validate(socks);
        SocksModel socksFromTable = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksFromTable != null && socksFromTable.getQuantity() - socks.getQuantity() >= 0) {
            socksFromTable.setQuantity(socksFromTable.getQuantity() - socks.getQuantity());
            socksRepository.save(socksFromTable);
            return true;
        } else {
            throw new NoSuchSocksQuantityException();
        }
    }

    @Override
    public int findSocksByParameters(String color, String operation, int cottonPart) {
        int quantity = 0;
        List<SocksModel> listOfSocks;
        switch (operation) {
            case "moreThan":
                listOfSocks = socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
                if (listOfSocks.size() == 0) throw new SocksNotFoundException();
                else quantity = listOfSocks.stream().mapToInt(SocksModel::getQuantity).sum();
                break;
            case "lessThan":
                listOfSocks = socksRepository.findByColorAndCottonPartIsLessThan(color, cottonPart);
                if (listOfSocks.size() == 0) throw new SocksNotFoundException();
                else quantity = listOfSocks.stream().mapToInt(SocksModel::getQuantity).sum();
                break;
            case "equal":
                listOfSocks = socksRepository.findByColorAndCottonPartEquals(color, cottonPart);
                if (listOfSocks.size() == 0) throw new SocksNotFoundException();
                else quantity = listOfSocks.stream().mapToInt(SocksModel::getQuantity).sum();
                break;
            default:
                throw new NoSuchOperationException();
        }
        return quantity;
    }

    /**
     * Валидация входных данных. Проверка на цвет, количество, содержание хлопка
     * @param socks - объект носки, переданные в запросе
     */
    private void validate(SocksModel socks) {
        if (socks.getColor() == null) throw new BadSocksException("invalid color");
        else if (socks.getQuantity() < 0) throw new BadSocksException("Invalid quantity");
        else if (socks.getCottonPart() < 0 || socks.getCottonPart() > 100) throw new BadSocksException();
    }
}
