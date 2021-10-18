package ru.raiffeisen.cibinternstesttask.operations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
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

    static Operation findOperationOrThrow(List<Operation> opList, String opName) {
        return opList.stream()
                .filter(o -> opName.equals(o.getOperationName()))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Unsupported operation"));
    }

}
