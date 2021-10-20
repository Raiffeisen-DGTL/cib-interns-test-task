package ru.raiffeisen.cibinternstesttask.socks;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.cibinternstesttask.socks.dto.QuantityDto;
import ru.raiffeisen.cibinternstesttask.socks.dto.SocksDto;

/**
 * Предоставляет интерфейс для работы с объектами Socks.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SocksController {

    private final SocksService socksService;

    /**
     * Приход носков. Добавляет на склад указанное количество носков
     * с определенным цветом и содержанием хлопка.
     * Если указанного типа носков нет на складе, создает этот тип.
     * Возвращает HTTP 200 с пустым телом если операция успешна,
     * HTTP 400 с описанием ошибки, в случае ошибок в запросе.
     *
     * @param socks JSON
     */
    @PostMapping("/socks/income")
    @ResponseStatus(HttpStatus.OK)
    public void income(@Valid @RequestBody SocksDto socks) {
        socksService.income(socks);
    }

    /**
     * Расход носков. Уменьшает количество носков с определенным цветом
     * и содержанием хлопка на складе.
     * Если указанного типа носков нет на складе, возвращает HTTP 400.
     * Если количество в запросе больше, чем есть на складе - HTTP 400.
     * Возвращает HTTP 200 с пустым телом если операция успешна,
     * HTTP 400 с описанием ошибки, в случае ошибок в запросе.
     *
     * @param socks JSON
     */
    @PostMapping("/socks/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void outcome(@Valid @RequestBody SocksDto socks) {
        socksService.outcome(socks);
    }

    /**
     * Возвращает количество носков на складе,
     * которые соответствуют параметрам, переданным в запросе.
     * Параметры:
     *  - color — цвет носков, строка;
     *  - operation — оператор сравнения значения количества хлопка в составе носков,
     * одно значение из: moreThan, lessThan, equal;
     *  - cottonPart — значение процента хлопка в составе носков из сравнения,
     * целое число от 0 до 100.
     *
     * @param color название цвета
     * @param operation название операции
     * @param cottonPart содержание хлопка
     * @return JSON
     */
    @GetMapping("/socks")
    public QuantityDto getAllSocks(@RequestParam String color,
                                   @RequestParam String operation,
                                   @RequestParam Short cottonPart) {
        return socksService.getSocksQuantity(color, operation, cottonPart);
    }
}
