package ru.raiffeisen.cibinternstesttask.operations;

import java.util.List;
import ru.raiffeisen.cibinternstesttask.socks.Color;
import ru.raiffeisen.cibinternstesttask.socks.Socks;
import ru.raiffeisen.cibinternstesttask.socks.SocksRepository;

public record LessThan(SocksRepository socksRepository) implements Operation {

    private static final String OP_NAME = "lessThan";

    @Override
    public String getOperationName() {
        return OP_NAME;
    }

    @Override
    public List<Socks> getSocks(Color color, Short cottonPart) {
        return socksRepository
                .findSocksByColorAndCottonPartLessThan(color, cottonPart);
    }
}
