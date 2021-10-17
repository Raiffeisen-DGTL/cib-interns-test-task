package ru.raiffeisen.cibinternstesttask.operations;

import java.util.ArrayList;
import java.util.List;
import ru.raiffeisen.cibinternstesttask.socks.Color;
import ru.raiffeisen.cibinternstesttask.socks.Socks;
import ru.raiffeisen.cibinternstesttask.socks.SocksRepository;

public record Equal(SocksRepository socksRepository) implements Operation {

    private static final String OP_NAME = "equal";

    @Override
    public String getOperationName() {
        return OP_NAME;
    }

    @Override
    public List<Socks> getSocks(Color color, Short cottonPart) {
        List<Socks> result = new ArrayList<>();
        socksRepository.findSocksByColorAndCottonPart(color, cottonPart)
                .ifPresent(result::add);
        return result;
    }
}
