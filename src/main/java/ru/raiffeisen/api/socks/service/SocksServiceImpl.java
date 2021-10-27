package ru.raiffeisen.api.socks.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raiffeisen.api.socks.Body;
import ru.raiffeisen.api.socks.OperationEnum;
import ru.raiffeisen.api.socks.entity.Socks;
import ru.raiffeisen.api.socks.exception_handling.NoCorrectParameterException;
import ru.raiffeisen.api.socks.repository.SocksRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository repository;

    @Override
    public List<Socks> getAllDefinedOperation(String color, OperationEnum operationEnum,
                                              int cottonPart) throws NoCorrectParameterException {
        switch (operationEnum) {
            case MORE_THAN:
                return repository.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
            case LESS_THAN:
                return repository.findAllByColorAndCottonPartLessThan(color, cottonPart);
            case EQUAL:
                return repository.findAllByColorAndCottonPart(color, cottonPart);
            default:
                throw new NoCorrectParameterException("Проверте значение operation");
        }
    }

    @Override
    public Socks socksIncome(Body body) {
        String color = body.getColor();
        int cottonPart = body.getCottonPart();
        int quantity = body.getQuantity();
        Socks socks = repository.findByColorAndCottonPart(color, cottonPart);
        if (socks != null) {
            int newQuantity = socks.getQuantity() + quantity;
            socks.setQuantity(newQuantity);
        }
        else {
            socks = create(color, cottonPart, quantity);
        }
        repository.save(socks);
        return socks;
    }

    @Override
    public synchronized Socks socksOutcome(Body body) throws NoCorrectParameterException {
        String color = body.getColor();
        int cottonPart = body.getCottonPart();
        int quantity = body.getQuantity();
        Socks socks = repository.findByColorAndCottonPart(color, cottonPart);
        if (socks != null) {
            if (socks.getQuantity() > quantity) {
                int newQuantity = socks.getQuantity() - quantity;
                socks.setQuantity(newQuantity);
                repository.save(socks);
                return socks;
            }
            else {
                throw new NoCorrectParameterException(quantity + " такого количество носков на скаладе нет");
            }
        }
        else {
            throw new NoCorrectParameterException("Носков с указанным цветом и содержанием хлопка нет на складе");
        }
    }

    @Override
    public Socks create(String color, int cottonPart, int quantity) {
        return new Socks(color, cottonPart, quantity);
    }
}
