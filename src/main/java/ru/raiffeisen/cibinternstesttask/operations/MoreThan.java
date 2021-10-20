package ru.raiffeisen.cibinternstesttask.operations;

import java.util.List;
import ru.raiffeisen.cibinternstesttask.socks.Color;
import ru.raiffeisen.cibinternstesttask.socks.Socks;
import ru.raiffeisen.cibinternstesttask.socks.SocksRepository;

/**
 * Операция сравнения moreThan, возвращает список Socks с содержанием хлопка больше,
 * чем указано в cottonPart.
 * Используется для вычисления количества носков в запросе SocksController.getAllSocks.
 */
public record MoreThan(SocksRepository socksRepository) implements Operation {

    private static final String OP_NAME = "moreThan";

    @Override
    public String getOperationName() {
        return OP_NAME;
    }

    @Override
    public List<Socks> getSocks(Color color, Short cottonPart) {
        return socksRepository
                .findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
    }
}
