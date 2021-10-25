package ru.raiffeisen.socks.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.raiffeisen.socks.dto.SocksQuantityRequest;
import ru.raiffeisen.socks.dto.SocksRequest;
import ru.raiffeisen.socks.dto.SocksResponse;
import ru.raiffeisen.socks.dto.ErrorResponse;

@Api(tags = "Операции для работы с носками")
public interface SockApi {

    @ApiOperation("Регистрирует приход носков на склад")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Удалось добавить приход"),
            @ApiResponse(code = 400, message = "Параметры запроса отсутствуют или имеют некорректный формат", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Произошла ошибка, не зависящая от вызывающей стороны")})
    void registerArrivalOfSocks(@RequestBody SocksRequest socksRequest);

    @ApiOperation("Регистрирует отпуск носков со склада")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Удалось выполнить отпуск"),
            @ApiResponse(code = 400, message = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(code = 500, message = "Произошла ошибка, не зависящая от вызывающей стороны")})
    void registerReleaseOfSocks(@RequestBody SocksRequest socksRequest);

    @ApiOperation("Возвращает общее количество носков на складе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Запрос выполнен"),
            @ApiResponse(code = 400, message = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(code = 500, message = "Произошла ошибка, не зависящая от вызывающей стороны")})
    ResponseEntity<SocksResponse> getQuantityOfSocks(SocksQuantityRequest request);
}
