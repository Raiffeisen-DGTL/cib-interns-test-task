package com.java.task.service;

import com.java.task.entity.Color;
import com.java.task.entity.Socks;
import com.java.task.exception.OutcomeFromNotExistingSocksException;
import com.java.task.repository.ColorRepository;
import com.java.task.repository.SocksRepository;
import com.java.task.request.SocksByParamsRequest;
import com.java.task.request.SocksRequest;
import com.java.task.response.SocksResponse;
import com.raiffeisen.task.exception.OutcomeMoreThenRemainderSocksException;
import com.raiffeisen.task.exception.UnsupportableOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class SocksService {
    private final ColorRepository colorRepository;
    private final SocksRepository socksRepository;

    @Transactional
    public SocksResponse incomeSocks(SocksRequest socksRequest) {
        Color colorEntity = colorRepository.findByColor(socksRequest.getColor());
        Socks socks;
        if (isNull(colorEntity)) { // Если цвет носка не существует, значит таких носков точно нет в базе
            colorEntity = new Color(socksRequest.getColor());
            colorRepository.save(colorEntity); // Сохраним новый цвет
            socks = new Socks(colorEntity.getId(), socksRequest.getCottonPart(), socksRequest.getQuantity());
            return new SocksResponse(colorEntity.getColor(), socks.getCottonPart(), socks.getQuantity());
        }
        // Иначе пробуем найти носок
        socks = socksRepository.findByColorIdAndCottonPart(colorEntity.getId(), socksRequest.getCottonPart());
        if (nonNull(socks)) { // Если носок есть в базе, прибавим к нему новые пары
            socks.setQuantity(socks.getQuantity() + socksRequest.getQuantity());
            socksRepository.save(socks);
            return new SocksResponse(colorEntity.getColor(), socks.getCottonPart(), socks.getQuantity());
        }
        // Иначе создадим новую запись о данной паре носков с указанным количеством пар
        socks = new Socks(colorEntity.getId(), socksRequest.getCottonPart(), socksRequest.getQuantity());
        socksRepository.save(socks);
        return new SocksResponse(colorEntity.getColor(), socks.getCottonPart(), socks.getQuantity());
    }

    @Transactional
    public SocksResponse outcomeSocks(SocksRequest socksRequest) {
        Color colorEntity = colorRepository.findByColor(socksRequest.getColor());
        Socks socks;
        if (isNull(colorEntity)) { // Если цвет носка не существует, значит его точно нет в базе, так как он должен обязательно ссылаться на цвет
            // Если происходит отпуск пар носков из несуществующего вида носков
            throw new OutcomeFromNotExistingSocksException(
                    "Попытка изъять носки из несуществующего вида носков.");
        }
        socks = socksRepository.findByColorIdAndCottonPart(colorEntity.getId(), socksRequest.getCottonPart());
        if (isNull(socks)) { // Если такого вида носков все равно нет в базе, выдать исключение
            throw new OutcomeFromNotExistingSocksException("Попытка изъять носки из несуществующего вида носков.");
        }
        // Если число оставшихся пар больше либо равно вычитаемому, осуществить операцию
        if (socks.getQuantity() >= socksRequest.getQuantity()) {
            socks.setQuantity(socks.getQuantity() - socksRequest.getQuantity());
            socksRepository.save(socks);
            return new SocksResponse(colorEntity.getColor(), socks.getCottonPart(), socks.getQuantity());
        }
        throw new OutcomeMoreThenRemainderSocksException("Попытка изъять больше носков, чем есть на складе.");
    }

    public Integer getSocksValueByParams(SocksByParamsRequest socksByParamsRequest) {
        Color colorEntity = colorRepository.findByColor(socksByParamsRequest.getColor());
        List<Socks> socksList;
        int socksValue = 0;
        if (isNull(colorEntity)) { // Если цвет носка отсутствует в базе, значит носков такого типа нет и считать их бессмысленно
            return socksValue;
        }
        switch (socksByParamsRequest.getOperation()) {
            case "moreThan":
                socksList = socksRepository.findByColorIdAndCottonPartGreaterThan(
                        colorEntity.getId(), socksByParamsRequest.getCottonPart());
                break;
            case "lessThan":
                socksList = socksRepository.findByColorIdAndCottonPartLessThan(
                        colorEntity.getId(), socksByParamsRequest.getCottonPart());
                break;
            case "equal":
                socksList = socksRepository.findByColorIdAndCottonPartEquals(
                        colorEntity.getId(), socksByParamsRequest.getCottonPart());
                break;
            default:
                throw new UnsupportableOperationException(
                        "Получена некорректная операция: ", socksByParamsRequest.getOperation());
        }
        socksValue = calculateSocksValue(socksList);
        return socksValue;
    }

    private int calculateSocksValue(List<Socks> socksList) {
        int socksValue = 0;
        for (Socks socks : socksList) {
            socksValue += socks.getQuantity();
        }
        return socksValue;
    }
}
