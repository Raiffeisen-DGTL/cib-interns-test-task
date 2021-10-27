package ru.raiffeisen.api.socks.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.api.socks.Body;
import ru.raiffeisen.api.socks.OperationEnum;
import ru.raiffeisen.api.socks.entity.Socks;
import ru.raiffeisen.api.socks.exception_handling.NoCorrectParameterException;
import ru.raiffeisen.api.socks.exception_handling.RequestData;
import ru.raiffeisen.api.socks.service.SocksServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RESTController {
    private final SocksServiceImpl socksService;

    @PostMapping("/socks/income")
    public RequestData socksIncome(@RequestBody Body body) {
        if (isCorrectParam(body)) {
            Socks socks = socksService.socksIncome(body);
            RequestData data = new RequestData();
            if (socks.getQuantity() == body.getQuantity()) {
                data.setInfo("На склад добавлены новая позиция носков в количестве " + socks.getQuantity() + " шт.");
            }
            else {
                data.setInfo("На склад добавлены новые носки в количестве " + socks.getQuantity() + " шт.");
            }
            return data;
        }
        else {
            throw new NoCorrectParameterException("Параметры запроса отсутствуют или имеют некорректный формат");
        }
    }

    @PostMapping("/socks/outcome")
    public RequestData socksOutcome(@RequestBody Body body) {
        if (isCorrectParam(body)) {
            Socks socks = socksService.socksOutcome(body);
            return new RequestData("На складе осталось " + socks.getQuantity() + " носков");
        }
        else {
            throw new NoCorrectParameterException("Параметры запроса отсутствуют или имеют некорректный формат");
        }
    }

    @GetMapping ("/socks")
    public RequestData showAllSocksByOperation(@RequestParam("color") String color,
                                               @RequestParam("operation") String operation,
                                               @RequestParam("cottonPart") int cottonPart) {
        OperationEnum operationEnum = OperationEnum.GET.operationEnum(operation);
        boolean isCorrectParam = cottonPart > 0 && cottonPart <= 100 && operationEnum != OperationEnum.NO_CORRECT;
        if (isCorrectParam) {
            List<Socks> socksList = socksService.getAllDefinedOperation(color, operationEnum, cottonPart);
            RequestData data = new RequestData();
            String countSocks = socksList.stream()
                    .map(Socks::getQuantity)
                    .reduce(Integer::sum)
                    .orElse(0)
                    .toString();
            data.setInfo(countSocks);
            return data;
        }
        else {
            throw new NoCorrectParameterException("Параметры запроса отсутствуют или имеют некорректный формат");
        }
    }

    @ExceptionHandler
    public ResponseEntity<RequestData> handleException(NoCorrectParameterException exception) {
        RequestData date = new RequestData(exception.getMessage());
        return new ResponseEntity<>(date, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<RequestData> handleInvalidFormatException(InvalidFormatException exception) {
        RequestData date = new RequestData(exception.getMessage());
        return new ResponseEntity<>(date, HttpStatus.BAD_REQUEST);
    }

    private boolean isCorrectParam(Body body) {
        return body.getColor() != null && body.getCottonPart() > 0 && body.getCottonPart() <= 100 &&
                body.getQuantity() > 0;
    }
}
