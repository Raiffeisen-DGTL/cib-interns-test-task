package com.raiffeisensocks.app.service;

import com.raiffeisensocks.app.dto.SocksDto;
import com.raiffeisensocks.app.model.Socks;
import com.raiffeisensocks.app.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public Integer getGeneralSocksQuantity(List<Socks> socksList) {
        return  socksList.stream()
                .map(Socks::getQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public void addIncome(SocksDto socksDto) {
        List<Socks> socksList = socksRepository.findSocksByColorAndCottonPart(socksDto.getColor(),
                                                                              socksDto.getCottonPart());
        if (socksList.isEmpty()) {
            Socks newSocks = new Socks();
            newSocks.setColor(socksDto.getColor());
            newSocks.setCottonPart(socksDto.getCottonPart());
            newSocks.setQuantity(socksDto.getQuantity());
            socksRepository.save(newSocks);
        } else {
            // Находим однозначно по заданному Color и CottonPart
            Socks socks = socksList.get(0);
            socks.setQuantity(socks.getQuantity() + socksDto.getQuantity());
            socksRepository.save(socks);
        }
    }

    @Override
    public void addOutcome(SocksDto socksDto) {
        List<Socks> socksList = socksRepository.findSocksByColorAndCottonPart(socksDto.getColor(),
                                                                              socksDto.getCottonPart());

        if (socksList.isEmpty() || socksList.get(0).getQuantity() < socksDto.getQuantity()) {
            throw new ConstraintViolationException("We don't have such SOCKS or so MANY", new HashSet<ConstraintViolation<?>>());
        } else {
            Socks updatedSocks = socksList.get(0);
            updatedSocks.setQuantity(updatedSocks.getQuantity() - socksDto.getQuantity());
            socksRepository.save(updatedSocks);
        }
    }

    @Override
    public Integer getAllSocksByParams(String color, String operation, Integer cottonPart) {
        switch (operation) {
            case "moreThan" -> {
                return getGeneralSocksQuantity(socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart));
            }
            case "lessThan" -> {
                return getGeneralSocksQuantity(socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart));
            }
            case "equal" -> {
                return getGeneralSocksQuantity(socksRepository.findSocksByColorAndCottonPartEquals(color, cottonPart));
            }
            default -> {
                throw new ConstraintViolationException("Not found this operation in ['equal', 'moreThan', 'lessThan']",
                        new HashSet<ConstraintViolation<?>>());
            }
        }
    }
}
