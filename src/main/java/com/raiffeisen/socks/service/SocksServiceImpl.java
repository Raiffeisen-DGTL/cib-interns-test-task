package com.raiffeisen.socks.service;

import com.raiffeisen.socks.dao.SocksRepository;
import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.exceptions.IncorrectSockFormatException;
import com.raiffeisen.socks.exceptions.NotFoundSockException;
import com.raiffeisen.socks.model.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository repository;

    @Autowired
    public SocksServiceImpl(SocksRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void registerSocks(@NonNull SockDto sockDto) {
        sockDtoValidation(sockDto);
        repository.getSockByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart())
                .ifPresentOrElse(sock -> {
                            sock.setQuantity(sock.getQuantity() + sockDto.getQuantity());
                            repository.save(sock);
                        }, () -> repository.save(createNewSockFromDto(sockDto))
                );
    }

    @Transactional
    @Override
    public void outcomeSocks(@NonNull SockDto sockDto) {
        sockDtoValidation(sockDto);
        repository.getSockByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart())
                .ifPresentOrElse(sock -> deleteCertainQuantityOfSocks(sock, sockDto.getQuantity()),
                        () -> {
                            throw new NotFoundSockException("Носки с заданными параметрами не найдены");
                        }
                );
    }

    @Transactional
    @Override
    public SockDto getSocksByParams(String color, String operation, Integer cottonPart) {
        final SockDto sockDto = new SockDto();
        sockDto.setColor(color);
        sockDto.setCottonPart(cottonPart);
        sockDto.setQuantity(0);
        List<Sock> sockList = getSocksListsByOperation(color, operation, cottonPart);
        if (sockList.isEmpty()) {
            throw new NotFoundSockException("Носки с данными параметрами не найдены");
        }
        sockList.forEach(sock -> sockDto.setQuantity(sock.getQuantity() + sockDto.getQuantity()));
        return sockDto;
    }

    private List<Sock> getSocksListsByOperation(String color, String operation, Integer cottonPart) {
        switch (operation) {
            case "equal":
                return repository.findByColorAndCottonPartEquals(color, cottonPart);
            case "lessThan":
                return repository.findByColorAndCottonPartLessThan(color, cottonPart);
            case "moreThan":
                return repository.findByColorAndCottonPartGreaterThan(color, cottonPart);
            default:
                throw new IncorrectSockFormatException("Некорректный оператор");
        }
    }

    private void sockDtoValidation(SockDto sockDto) {
        if (sockDto == null || sockDto.getColor() == null
                || sockDto.getCottonPart() == null
                || sockDto.getQuantity() == null || sockDto.getQuantity() <= 0
                || sockDto.getCottonPart() < 0 || sockDto.getCottonPart() > 100) {
            throw new IncorrectSockFormatException("Некорректный формат объекта");
        }
    }

    private Sock createNewSockFromDto(SockDto sockDto) {
        final Sock newSock = new Sock();
        newSock.setColor(sockDto.getColor());
        newSock.setCottonPart(sockDto.getCottonPart());
        newSock.setQuantity(sockDto.getQuantity());
        return newSock;
    }

    private void deleteCertainQuantityOfSocks(Sock sock, Integer quantity) {
        Integer quantityValueFromDB = sock.getQuantity();
        if (quantityValueFromDB < quantity) {
            throw new IncorrectSockFormatException("Такого количества на складе нет");
        }
        if (quantityValueFromDB.equals(quantity)) {
            repository.delete(sock);
        } else {
            sock.setQuantity(quantityValueFromDB - quantity);
            repository.save(sock);
        }
    }
}
