package com.raiffeisen.socks.service;

import com.raiffeisen.socks.dao.SocksRepository;
import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.exceptions.IncorrectSockFormatException;
import com.raiffeisen.socks.exceptions.NotFoundSockException;
import com.raiffeisen.socks.model.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocksService {
    private final SocksRepository repository;

    @Autowired
    public SocksService(SocksRepository repository) {
        this.repository = repository;
    }

    public void registerSocks(SockDto sockDto) {
        sockDtoValidation(sockDto);
        repository.getSockByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart())
                .ifPresentOrElse(sock -> {
                            sock.setQuantity(sock.getQuantity() + sockDto.getQuantity());
                            repository.save(sock);
                        },
                        () -> {
                            final Sock newSock = new Sock();
                            newSock.setColor(sockDto.getColor());
                            newSock.setCottonPart(sockDto.getCottonPart());
                            newSock.setQuantity(sockDto.getQuantity());
                            repository.save(newSock);
                        }
                );
    }

    public void outcomeSocks(SockDto sockDto) {
        sockDtoValidation(sockDto);
        repository.getSockByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart())
                .ifPresentOrElse(sock -> {
                            Integer valueFromDB = sock.getQuantity();
                            Integer valueCurrent = sockDto.getQuantity();
                            if (valueFromDB < valueCurrent) {
                                throw new IncorrectSockFormatException("Неверное количество удаляемых пар носков");
                            }
                            if (valueFromDB.equals(valueCurrent)) {
                                repository.delete(sock);
                            } else {
                                sock.setQuantity(valueFromDB - sockDto.getQuantity());
                                repository.save(sock);
                            }
                        },
                        () -> {
                            throw new NotFoundSockException("Носки с заданными параметрами не найдены");
                        }
                );
    }

    private void sockDtoValidation(SockDto sockDto) {
        if (sockDto == null || sockDto.getColor() == null
                || sockDto.getCottonPart() == null
                || sockDto.getQuantity() == null
                || sockDto.getCottonPart() < 0 || sockDto.getCottonPart() > 100) {
            throw new IncorrectSockFormatException("Некорректный формат объекта");
        }
    }
}
