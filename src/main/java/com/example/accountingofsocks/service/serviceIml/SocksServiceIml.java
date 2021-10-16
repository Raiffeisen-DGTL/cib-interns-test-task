package com.example.accountingofsocks.service.serviceIml;

import com.example.accountingofsocks.exception.NullQuantityPointerException;
import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import com.example.accountingofsocks.repository.SocksDao;
import com.example.accountingofsocks.service.abstr.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.OptionalInt;

@Service
public class SocksServiceIml implements SocksService {

    private SocksDao dao;

    @Autowired
    public SocksServiceIml(SocksDao dao) {
        this.dao = dao;
    }

    @Override
    public Socks income(Socks socks) throws NullQuantityPointerException {
        List<Socks> socksList = dao.findAllByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());

        if (socksList.size() != 0) {
            Socks actualSocks = socksList.get(0);
            actualSocks.setQuantity(actualSocks.getQuantity() + socks.getQuantity());
            return dao.save(actualSocks);
        } else if (socks.getQuantity() < 1) {
            throw new NullQuantityPointerException("Нельзя поставить на учет, нулевое количество носков ");
        }
        return dao.save(socks);
    }

    @Override
    public void outcome(Socks socks) throws QuantitySocksOutOfBoundsException {
        List<Socks> socksList = dao.findAllByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());
        if (socksList.size() != 0) {
            Socks actualSocks = socksList.get(0);
            if (actualSocks.getQuantity() < socks.getQuantity()) {
                throw new QuantitySocksOutOfBoundsException("Нельзя списать количество пар носков больше, чем есть на складе");
            } else if (actualSocks.getQuantity() > socks.getQuantity()) {
                actualSocks.setQuantity(actualSocks.getQuantity() - socks.getQuantity());
                dao.save(actualSocks);
            } else {
                dao.delete(socksList.get(0));
            }
        } else {
            throw new QuantitySocksOutOfBoundsException("Нельзя списать со склада то, чего нет");
        }
    }

    @Override
    public List<Socks> findByParameters(String color, Operation operation, byte cottonPart) {
        return switch (operation) {
            case LESSTHAN -> dao.findAllByColorAndCottonPartIsLessThan(color, cottonPart);
            case EQUAL -> dao.findAllByColorAndCottonPartEquals(color, cottonPart);
            case MORETHAN -> dao.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
        };
    }

    @Override
    public OptionalInt getNumberSocksByParameters(String color, Operation operation, byte cottonPart) {
        List<Socks> socksList = findByParameters(color, operation, cottonPart);

        return socksList.stream()
                .mapToInt(socks -> socks.getQuantity())
                .reduce((acc, x) -> acc + x);
    }
}
