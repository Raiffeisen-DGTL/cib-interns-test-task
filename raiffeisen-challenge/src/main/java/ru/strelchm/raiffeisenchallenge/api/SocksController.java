package ru.strelchm.raiffeisenchallenge.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.digital_mind.marketplace.domain.mongo.DataHubRequest;
import ru.digital_mind.marketplace.domain.mongo.Dataset;
import ru.digital_mind.marketplace.domain.mongo.RequestProperty;
import ru.digital_mind.marketplace.dto.DataHubRequestDto;
import ru.digital_mind.marketplace.dto.UserContext;
import ru.digital_mind.marketplace.exception.NotFoundException;
import ru.digital_mind.marketplace.service.StorageService;
import ru.digital_mind.marketplace.service.UserService;
import ru.digital_mind.marketplace.service.impl.*;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.dto.InOutComeSockDto;
import ru.strelchm.raiffeisenchallenge.service.impl.SockServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api("REST controller 4 DatahubRequest operations")
@RequestMapping("/api/datahub")
@Validated
//@PreAuthorize("hasAnyRole()") todo - держать открытой регистрацию
public class SocksController extends ParentController {
    private final SockServiceImpl sockService;



    .

    Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

    color — цвет носков, строка (например, black, red, yellow);
    cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
    quantity — количество пар носков, целое число больше 0.

    Результаты:

    HTTP 200 — удалось добавить приход;
    HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
    HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).



    @Autowired
    public SocksController(SockServiceImpl sockService) {
        this.sockService = sockService;
    }

    @GetMapping
    @ApiOperation("Общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    public List<Sock> getSocksCount(
            @NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable InOutComeSockDto incomeSockDto,
            @NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable InOutComeSockDto incomeSockDto,
            @NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable InOutComeSockDto incomeSockDto

                    color — цвет носков, строка;
                    operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;
                    cottonPart — значение процента хлопка в составе носков из сравнения.

    ) {
        return sockService.getAll();
    }

    @PostMapping("/income")
    @ApiOperation("Регистрация прихода носков на склад")
    public Sock sockOutcome(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @RequestBody InOutComeSockDto incomeSockDto) {
        return sockService.createSock(new Sock(stringDto.getName()));
    }

    @PostMapping("/outcome")
    @ApiOperation("Регистрация отпуска носков на склад")
    public Sock sockIncome(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @RequestBody InOutComeSockDto outcomeSockDto) {
        return sockService.createSock(new Sock(stringDto.getName()));
    }

    @GetMapping("/{id}")
    public Sock getSockById(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable String id) {
        return sockService.getById(id).orElseThrow(() -> new NotFoundException("Sock with " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSock(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable String id,
                          @ModelAttribute(USER_CONTEXT) UserContext userContext) {
        sockService.deleteSock(id);
    }
}
