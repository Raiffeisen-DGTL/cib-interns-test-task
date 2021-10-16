package com.example.raif.service;

import com.example.raif.entity.SocksEntity;
import com.example.raif.exception.IncorrectParametersException;
import com.example.raif.exception.SocksNotFoundException;
import com.example.raif.exception.SocksQuantityOutOfRangeException;
import com.example.raif.repository.SocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksService {
    @Autowired
    SocksRepo socksRepo;

    public String addSocks(SocksEntity socks) {
        SocksEntity existingSocks = socksRepo.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (existingSocks != null) {
            existingSocks.setQuantity(existingSocks.getQuantity() + socks.getQuantity());
            socksRepo.save(existingSocks);
        } else {
            socksRepo.save(socks);
        }
        return "Носки успешно добавлены";
    }

    public String deleteSocks(SocksEntity socks) throws SocksNotFoundException, SocksQuantityOutOfRangeException {
        SocksEntity existingSocks = socksRepo.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (existingSocks != null) {
            if (existingSocks.getQuantity() < socks.getQuantity())
                throw new SocksQuantityOutOfRangeException("Запрошено слишком большое количество носков");
            if (existingSocks.getQuantity() == socks.getQuantity()) {
                socksRepo.delete(existingSocks);
            } else {
                existingSocks.setQuantity(existingSocks.getQuantity() - socks.getQuantity());
                socksRepo.save(existingSocks);
            }
            return "Носки успешно изьяты";
        } else {
            throw new SocksNotFoundException("носки по заданным параметрам не представлены на складе");
        }
    }

    public List<SocksEntity> getSocks(String color, String operation, int cottonPart) throws IncorrectParametersException {
        if (color.isBlank() || operation.isBlank() || cottonPart < 0 || cottonPart > 100)
            throw new IncorrectParametersException("неверные параметры запроса");

        switch (operation) {
            case "moreThan":
                return socksRepo.findByColorAndCottonPartGreaterThan(color, cottonPart);
            case "lessThan":
                return socksRepo.findByColorAndCottonPartLessThan(color, cottonPart);
            case "equals":
                return socksRepo.findByColorAndCottonPartEquals(color, cottonPart);
            default: throw new IncorrectParametersException("неверные параметры запроса");
        }
    }
}

