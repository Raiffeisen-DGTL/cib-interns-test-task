package com.raif.app.controller;

import com.raif.app.controller.dto.OperationRequestParam;
import com.raif.app.controller.dto.SocksIncomeDTO;
import com.raif.app.controller.dto.SocksOutcomeDTO;
import com.raif.app.service.SocksStorageRequestFilter;
import com.raif.app.service.SocksStorageService;
import com.raif.app.service.model.OperationType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/api")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Отпуск носков зарегистрирован успешно"),
        @ApiResponse(code = 400, message = "Параметры запроса отсутствуют или имеют некорректный формат"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера"),
})
public class SocksStorageController {

    private final SocksStorageService socksStorageService;

    public SocksStorageController(SocksStorageService socksStorageService) {
        this.socksStorageService = socksStorageService;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Некорректный параметр: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Регистрирует приход носков на склад")
    @PostMapping("/socks/income")
    public ResponseEntity<Object> income(@Valid @RequestBody SocksIncomeDTO socksIncomeDTO) {
        socksStorageService.registerIncome(socksIncomeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(value = "Регистрирует отпуск носков со склада")
    @PostMapping("/socks/outcome")
    public ResponseEntity<Object> outcome(@Valid @RequestBody SocksOutcomeDTO socksIncomeDTO) {
        socksStorageService.registerOutcome(socksIncomeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation(value = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.")
    @GetMapping("/socks")
    public ResponseEntity<String> countSocks(@NotEmpty @RequestParam String color,
                                             @NotNull @RequestParam("operation") OperationRequestParam operation,
                                             @NotNull @Min(0) @Max(100) @RequestParam  Integer cottonPart
    ) {
        SocksStorageRequestFilter socksStorageRequestFilter = map(color, operation, cottonPart);
        Long socksNumber = socksStorageService.find(socksStorageRequestFilter);
        return ResponseEntity.status(HttpStatus.OK).body(socksNumber.toString());
    }

    private SocksStorageRequestFilter map(String color, OperationRequestParam operation, Integer cottonPart) {
        return new SocksStorageRequestFilter(color, OperationType.valueOf(operation.name()), cottonPart);
    }

}