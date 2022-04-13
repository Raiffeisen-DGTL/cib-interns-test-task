package com.example.socks.controller;

import com.example.socks.Util.Operations;
import com.example.socks.Util.SocksControllerExceptionHandler;
import com.example.socks.db.dto.SocksDTO;
import com.example.socks.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Validated
@Tag(name = "SocksController", description = "Главный контролер")
@SocksControllerExceptionHandler
public class SocksController {

    private final SocksService socksService;

    @Operation(
            summary = "Добавление носков",
            description = "Регистрирует приход носков на склад."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "удалось добавить приход"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)")
    }
    )
    @PostMapping(path = "/socks/income", produces = "application/json")
    public ResponseEntity<?> income(@Valid @RequestBody SocksDTO socks, BindingResult result) {

        try {
            socksService.saveSocks(socks);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("удалось добавить приход");
    }

    @Operation(
            summary = "Выдача носков.",
            description = "Регистрирует отпуск носков со склада."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "удалось выдать носки"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)")
    }
    )
    @PostMapping(path = "/socks/outcome", produces = "application/json")
    public ResponseEntity<?> outcome(@Valid @RequestBody SocksDTO socksDTO, BindingResult result) {
        try {
            var socks = socksService.getSocks(socksDTO);
            if (socks == null || socks.getQuantity() < socksDTO.getQuantity())
                return new ResponseEntity<>("Параметры запроса отсутствуют или имеют некорректный формат", HttpStatus.BAD_REQUEST);
            socksService.outcome(socks, socksDTO);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("удалось выдать носки");
    }

    @Operation(
            summary = "Возвращает общее количество носков на складе.",
            description = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа;"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)")
    }
    )
    @GetMapping(path = "/socks", produces = "application/json")
    public ResponseEntity<String> getSocks(@RequestParam(value = "color") @NotNull @NotBlank @Parameter(description = "Цвет носков")
                                                       String color ,
                                           @RequestParam(value = "operation")
                                           @Parameter(description = "оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;")
                                                   Operations operation,
                                           @Parameter(description = "значение процента хлопка в составе носков из сравнения.")
                                               @RequestParam(value = "cottonPart") @Min(0) @Max(100)
                                                       int cottonPart) {
        try {
            var socksCount = socksService.getSocksByOperation(color, operation, cottonPart);
            return new ResponseEntity<>(String.valueOf(socksCount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
