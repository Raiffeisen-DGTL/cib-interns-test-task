package ru.raiffeisen.cibinternstesttask.operations;

import java.util.ArrayList;
import java.util.List;
import ru.raiffeisen.cibinternstesttask.socks.Color;
import ru.raiffeisen.cibinternstesttask.socks.Socks;
import ru.raiffeisen.cibinternstesttask.socks.SocksRepository;

public interface Operation {

    List<Socks> getSocks(Color color, Short cottonPart);

    String getOperationName();

    static List<Operation> getOperationList(SocksRepository sc) {
        List<Operation> result = new ArrayList<>();
        result.add(new MoreThan(sc));
        result.add(new LessThan(sc));
        result.add(new Equal(sc));
        return result;
    }

}
