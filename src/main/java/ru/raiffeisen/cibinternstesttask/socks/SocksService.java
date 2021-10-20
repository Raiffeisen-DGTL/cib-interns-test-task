package ru.raiffeisen.cibinternstesttask.socks;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.raiffeisen.cibinternstesttask.operations.Operation;
import ru.raiffeisen.cibinternstesttask.socks.dto.QuantityDto;
import ru.raiffeisen.cibinternstesttask.socks.dto.SocksDto;

/**
 * Предоставляет методы для обработки запросов к Socks.
 */
@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository socksRepository;
    private final ColorRepository colorRepository;

    /**
     * Обрабатывает приход носков. Получает DTO и проверяет, есть ли носки с таким
     * сочетанием параметров в базе данных.
     * Если есть - увеличивает количество носков указанного типа.
     * Если подходящая запись не найдена - создает сущность с указанными параметрами.
     *
     * @param dto SocksDto
     */
    @Transactional
    public Socks income(SocksDto dto) {
        var color = colorRepository.save(Color.of(dto.color()));
        var socks = socksRepository
                .findSocksByColorAndCottonPart(color, dto.cottonPart())
                .orElseGet(() -> socksRepository.save(
                                Socks.of(color, dto.cottonPart(), dto.quantity())));
        increaseQty(dto, socks);
        return socks;
    }

    /**
     * Обрабатывает расход носков. Получает DTO и проверяет, есть ли носки с таким
     * сочетанием параметров в базе данных.
     * Если есть - уменьшает количество носков на указанное в запросе число.
     * Если нет - выбрасывает ResponseStatusException с HTTP 400.
     * Если количество в запросе больше чем есть базе, также выбрасывает исключение с HTTP 400.
     *
     * @param dto SocksDto
     */
    @Transactional
    public Socks outcome(SocksDto dto) {
        var color = getColorOrThrow(dto.color());
        var socks = socksRepository
                .findSocksByColorAndCottonPart(color, dto.cottonPart())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format(
                                "Socks with color: %s, cottonPart: %d not found",
                                color.getName(), dto.cottonPart())));
        decreaseQty(dto, socks);
        return socks;
    }

    /**
     * Возвращает количество носков, подходящих под указанные параметры.
     * Если подходящих записей в базе данных нет, возвращает ноль.
     * Параметры:
     * - color — цвет носков, строка;
     * - operation — оператор сравнения значения количества хлопка в составе носков,
     * одно значение из: moreThan, lessThan, equal;
     * - cottonPart — значение процента хлопка в составе носков из сравнения,
     * целое число от 0 до 100.
     *
     * @param colorName название цвета
     * @param opName название операции
     * @param cottonPart содержание хлопка
     * @return QuantityDto
     */
    public QuantityDto getSocksQuantity(String colorName, String opName, Short cottonPart) {
        checkCottonPart(cottonPart);
        var socksList = getOperation(opName)
                .getSocks(getColorOrThrow(colorName), cottonPart);
        var quantity = countSocks(socksList);
        return new QuantityDto(quantity);
    }

    /**
     * Возвращает сущность Color из базы данных или выбрасывает исключение.
     *
     * @param colorName название цвета
     * @return Color
     */
    private Color getColorOrThrow(String colorName) {
        return colorRepository
                .findByName(colorName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "No color found with name " + colorName));
    }

    /**
     * Увеличивает количество полученных носков на значение, указанное в DTO.
     *
     * @param dto dto
     * @param socks socks
     */
    private void increaseQty(SocksDto dto, Socks socks) {
        socks.setQuantity(socks.getQuantity() + dto.quantity());
    }

    /**
     * Уменьшает количество полученных носков на значение, указанное в DTO.
     * Выбрасывает исключение ResponseStatusException с кодом HTTP 400,
     * если носков в базе меньше, чем значение в DTO.
     *
     * @param dto dto
     * @param socks socks
     */
    private void decreaseQty(SocksDto dto, Socks socks) {
        var quantity = socks.getQuantity() - dto.quantity();
        if (quantity < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantity must be less or equal " + socks.getQuantity());
        }
        socks.setQuantity(quantity);
    }

    /**
     * Возвращает экземпляр класса, соответствующий названию операции или
     * выбрасывает ResponseStatusException с кодом HTTP 400.
     *
     * @param opName название операции
     * @return Operation
     */
    private Operation getOperation(String opName) {
        return Operation.findOperationOrThrow(
                Operation.getOperationList(socksRepository), opName);
    }

    /**
     * Проверяет количество хлопка соответствие диапазону значений 0 <= cottonPart <= 100.
     * Если не соответствует, выбрасывает исключение ResponseStatusException с HTTP 400.
     *
     * @param cottonPart количество хлопка
     */
    private void checkCottonPart(Short cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "cottonPart must be greater than 0 and less or equal 100");
        }
    }

    /**
     * Суммирует количество всех носков в переданном списке.
     *
     * @param socksList список с Socks
     * @return количество носков
     */
    private int countSocks(List<Socks> socksList) {
        var result = 0;
        for (var item : socksList) {
            result += item.getQuantity();
        }
        return result;
    }
}
