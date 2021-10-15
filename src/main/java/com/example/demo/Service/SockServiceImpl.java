package com.example.demo.Service;

import com.example.demo.Operation;
import com.example.demo.domain.Sock;
import com.example.demo.model.request.CreateSockRequest;
import com.example.demo.model.response.SockResponse;
import com.example.demo.repository.SockRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class SockServiceImpl implements SockService {

    @Autowired
    private SockRepository sockRepository;

    /**
     * Процедура регистрации товара
     *
     * @param request - json модель
     */
    @Override
    public void registerSockIncome(@NotNull CreateSockRequest request) {

        Sock sock = sockRepository
                .findByColorAndCottonPart(request.getColor(), request.getCottonPart())
                .orElse(new Sock().setQuantity(0));

        sockUpdate(sock, request, EndPointOperation.PLUS);

        buildSockResponse(sockRepository.save(sock));

    }

    /**
     * Процедура отгрузки товара
     *
     * @param request - json модель
     * @return boolean, true - если операция успешна, иначе false
     */
    @Override
    public boolean registerSockOutcome(@NotNull CreateSockRequest request) {

        Sock sock = sockRepository
                .findByColorAndCottonPart(request.getColor(), request.getCottonPart())
                .orElse(new Sock().setQuantity(0));

        sockUpdate(sock, request, EndPointOperation.MINUS);
        if (sock.getQuantity() >= 0) {
            buildSockResponse(sockRepository.save(sock));
            return true;
        } else {
            return false;
        }

    }

    /**
     * Функция получения колиества товара
     *
     * @param color      - цвет
     * @param operation  - тип операции сравнения MORETHAN, LESSTHAN, EUALQ
     * @param cottonPart - Процент хлопка. Целое числов в диапазоне от 0 до 100
     * @return Long
     */
    @Override
    public Long getSockQuantity(@NotNull String color, @NotNull String operation, @NotNull Integer cottonPart) {

        switch (Operation.valueOf(operation.toUpperCase())) {
            case MORETHAN:
                return sockRepository.getSockCountMoreThanCottonValue(color, cottonPart);
            case LESSTHAN:
                return sockRepository.getSockCountLessThanCottonValue(color, cottonPart);
            case EUALQ:
                return sockRepository.getSockCountEqualCottonValue(color, cottonPart);
            default:
                return -1L;
        }
    }

    /**
     * Процедура обновления товара
     *
     * @param sock              - обновляемый объект
     * @param request           - json модель объекта
     * @param endPointOperation - операция обновления
     */
    private void sockUpdate(Sock sock, CreateSockRequest request, EndPointOperation endPointOperation) {
//        ofNullable(sock.getId()).map(sock::setId);
        ofNullable(request.getColor()).map(sock::setColor);
        ofNullable(request.getCottonPart()).map(sock::setCottonPart);
        switch (endPointOperation) {
            case PLUS:
                ofNullable(request.getQuantity()).map(quantity -> sock.setQuantity(quantity + sock.getQuantity()));
                break;
            case MINUS:
                ofNullable(request.getQuantity()).map(quantity -> sock.setQuantity(sock.getQuantity() - quantity));
                break;
            case NONE:
                ofNullable(request.getQuantity()).map(sock::setQuantity);
                break;
        }
    }

    private Sock buildSockRequest(CreateSockRequest request) {
        return new Sock()
                .setColor(request.getColor())
                .setCottonPart(request.getCottonPart());
    }

    /**
     * Функиця изменения параметра объекта
     *
     * @param sock - объект
     * @return SockResponse
     */
    private SockResponse buildSockResponse(Sock sock) {
        return new SockResponse()
                .setColor(sock.getColor()).setCottonPart(sock.getCottonPart());
    }

}

enum EndPointOperation {
    PLUS,
    MINUS,
    NONE
}