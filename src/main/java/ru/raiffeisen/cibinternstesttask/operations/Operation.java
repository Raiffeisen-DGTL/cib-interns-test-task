package ru.raiffeisen.cibinternstesttask.operations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.raiffeisen.cibinternstesttask.socks.Color;
import ru.raiffeisen.cibinternstesttask.socks.Socks;
import ru.raiffeisen.cibinternstesttask.socks.SocksRepository;

/**
 * Операция сравнения для вычисления количества носков в запросе SocksController.getAllSocks
 */
public interface Operation {

    List<Socks> getSocks(Color color, Short cottonPart);

    String getOperationName();

    /**
     * Возвращает список всех доступных операций.
     *
     * @param sc репозиторий для доступа к Socks
     * @return список всех операций
     */
    static List<Operation> getOperationList(SocksRepository sc) {
        List<Operation> result = new ArrayList<>();
        result.add(new MoreThan(sc));
        result.add(new LessThan(sc));
        result.add(new Equal(sc));
        return result;
    }

    /**
     * Возвращает объект, выполняющий операцию, если название операции соответствует OP_NAME
     * из конкретного класса.
     * Если операция с указанным названием не существует в переданном списке,
     * выбрасывает исключение ResponseStatusException с HTTP 400.
     *
     * @param opList список операций
     * @param opName название операции
     * @return экземпляр класса, выполняющий конкретную операцию.
     */
    static Operation findOperationOrThrow(List<Operation> opList, String opName) {
        return opList.stream()
                .filter(o -> opName.equals(o.getOperationName()))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Unsupported operation"));
    }

}
