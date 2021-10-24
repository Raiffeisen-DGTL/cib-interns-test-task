package ru.itis.accountingSocks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.accountingSocks.dto.SocksDto;
import ru.itis.accountingSocks.forms.SocksForm;
import ru.itis.accountingSocks.models.Socks;
import ru.itis.accountingSocks.repositories.SocksRepository;

import static ru.itis.accountingSocks.dto.SocksDto.from;

@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    @Override
    public SocksDto addSocks(SocksForm socks) {


        Socks socksFromDb = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        if (socksFromDb != null) {
                socksFromDb.setColor(socks.getColor());
                socksFromDb.setCottonPart(socks.getCottonPart());
                socksFromDb.setQuantity(socksFromDb.getQuantity() + socks.getQuantity());
                socksRepository.save(socksFromDb);
                return from(socksFromDb);
        }

        Socks newSocks = null;

        if (socks.getQuantity() > 0 && socks.getCottonPart() > 0 && socks.getCottonPart() <= 100) {
            newSocks = new Socks();
            newSocks.setQuantity(socks.getQuantity());
            newSocks.setCottonPart(socks.getCottonPart());
            newSocks.setColor(socks.getColor());
            socksRepository.save(newSocks);

        } else {
            throw new IllegalArgumentException("Entered wrong data");
        }

        return from(newSocks);
    }

    @Override
    public SocksDto reduceSocks(SocksForm socks) {

        Socks socksFromDb = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        if (socksFromDb != null) {

                if (socksFromDb.getQuantity() != 0 && socks.getQuantity() <= socksFromDb.getQuantity()) {
                    socksFromDb.setColor(socks.getColor());
                    socksFromDb.setCottonPart(socks.getCottonPart());
                    socksFromDb.setQuantity(socksFromDb.getQuantity() - socks.getQuantity());
                    socksRepository.save(socksFromDb);
                } else {
                    throw new IllegalArgumentException("Entered a large quantity of socks");
                }

            }

            return from(socksFromDb);
        }

    @Override
    public int getTotalQuantitySocks(String color, String operation, int cottonPart) {
        int totalNumberSocks = 0;

        if (operation.equals("moreThan")) {
            totalNumberSocks = socksRepository.getQuantitySocksByColorEqualAndCottonPartMoreThan(color, cottonPart);
        }

        if (operation.equals("lessThan")) {
            totalNumberSocks = socksRepository.getQuantitySocksByColorEqualAndCottonPartLessThan(color, cottonPart);
        }

        if (operation.equals("equal")) {
            totalNumberSocks = socksRepository.getQuantitySocksByColorEqualAndCottonPartEqual(color, cottonPart);
        }
        return totalNumberSocks;
    }
}
