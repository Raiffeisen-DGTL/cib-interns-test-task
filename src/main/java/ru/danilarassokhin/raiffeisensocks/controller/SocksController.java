package ru.danilarassokhin.raiffeisensocks.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.apidocs.ErrorResponse;
import ru.danilarassokhin.raiffeisensocks.apidocs.SocksCountResponse;
import ru.danilarassokhin.raiffeisensocks.apidocs.SocksIncomeResponse;
import ru.danilarassokhin.raiffeisensocks.dto.*;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.mapper.SocksMapper;
import ru.danilarassokhin.raiffeisensocks.object.ValidationResult;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;
import ru.danilarassokhin.raiffeisensocks.service.dto.*;
import ru.danilarassokhin.raiffeisensocks.util.ValidationUtils;

import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.Socks;

/**
 * Controller for operations with {@link ru.danilarassokhin.raiffeisensocks.model.Socks}.
 */
@Api(tags = "Socks controller")
@RestController
@RequestMapping(API_ENDPOINT + Socks.ENDPOINT)
public class SocksController {

  private final SocksService sockService;

  @Autowired
  public SocksController(SocksService sockService) {
    this.sockService = sockService;
  }

  /**
   * Adds new socks to stock.
   *
   * @param socksIncomeDto Income data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto}
   * @return Response with current quantity of given socks color and cotton part
   * @throws DataValidityException If request data is not valid
   */
  @ApiOperation(value = "Income socks to stock")
  @Operation(
      summary = "Income socks to stock",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Successful income",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = SocksIncomeResponse.class)
                  )
              }),
          @ApiResponse(responseCode = "400",
              description = "Bad request. Some values were wrong",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ErrorResponse.class)
                  )
              }),
          @ApiResponse(responseCode = "500",
              description = "Internal server error occurred",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ErrorResponse.class)
                  )
              })
      }
  )
  @PostMapping(Socks.INCOME)
  public ResponseEntity<ResponseDto<Object>> incomeSocks(
      @Parameter(name = "SocksIncomeDto",
          description = "Socks income information", required = true)
      @RequestBody SocksIncomeDto socksIncomeDto)
      throws DataValidityException {
    ValidationResult validationResult = ValidationUtils.validate(socksIncomeDto);
    if (!validationResult.isValid()) {
      throw new DataValidityException(validationResult.getFirstErrorMessage());
    }
    SocksServiceIncomeDto socksServiceIncomeDto = SocksMapper.INSTANCE.controllerIncomeDtoToServiceDto(socksIncomeDto);
    SocksServiceResponse incomeResponse = sockService.income(socksServiceIncomeDto);
    return createResponse(incomeResponse);
  }

  /**
   * Removes some socks from stock.
   *
   * @param socksOutcomeDto Outcome
   *                        data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto}
   * @return Response with current quantity of given socks color and cotton part
   * @throws DataValidityException  If request data is not valid
   */
  @ApiOperation(value = "Outcome socks from stock")
  @Operation(
      summary = "Outcome socks from stock",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Successful outcome",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = SocksIncomeResponse.class)
                  )
              }),
          @ApiResponse(responseCode = "400",
              description = "Bad request. "
                  + "Some values were wrong or "
                  + "there is not enough socks in stock left",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ErrorResponse.class)
                  )
              }),
          @ApiResponse(responseCode = "500",
              description = "Internal server error occurred",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ErrorResponse.class)
                  )
              })
      }
  )
  @PostMapping(Socks.OUTCOME)
  public ResponseEntity<ResponseDto<Object>> outcomeSocks(
      @ApiParam(name = "SocksOutcomeDto", value = "Socks outcome information", required = true)
      @RequestBody SocksOutcomeDto socksOutcomeDto)
      throws DataValidityException {
    ValidationResult validationResult = ValidationUtils.validate(socksOutcomeDto);
    if (!validationResult.isValid()) {
      throw new DataValidityException(validationResult.getFirstErrorMessage());
    }
    SocksServiceOutcomeDto socksServiceOutcomeDto = SocksMapper.INSTANCE.controllerOutcomeDtoToServiceDto(socksOutcomeDto);
    SocksServiceResponse outcomeResponse = sockService.outcome(socksServiceOutcomeDto);
    return createResponse(outcomeResponse);
  }

  /**
   * Counts socks of given color and cotton part condition.
   *
   * @param color      Socks color to count
   * @param operation  Condition to use
   * @param cottonPart Cotton part to use in condition
   * @return Number of socks in stock for given condition
   * @throws DataValidityException If counting data is not valid
   */
  @ApiOperation(value = "Count socks")
  @Operation(
      summary = "Counts socks with given color and cotton part in stock",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Successful count",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = SocksCountResponse.class)
                  )
              }),
          @ApiResponse(responseCode = "400",
              description = "Bad request. Some values were wrong",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ErrorResponse.class)
                  )
              }),
          @ApiResponse(responseCode = "500",
              description = "Internal server error occurred",
              content = {
                  @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ErrorResponse.class)
                  )
              })
      }
  )
  @GetMapping
  public ResponseEntity<ResponseDto<Object>> countSocks(
      @ApiParam(name = "color", value = "Socks color to count", required = true)
      @RequestParam("color") String color,
      @ApiParam(name = "operation",
          value = "Operation for cotton part equality check", defaultValue = "")
      @RequestParam(value = "operation", required = false, defaultValue = "") String operation,
      @ApiParam(name = "cottonPart",
          value = "Cotton part to use in equality check", defaultValue = "0")
      @RequestParam(value = "cottonPart", required = false, defaultValue = "0") Byte cottonPart)
      throws DataValidityException {
    SocksSearchDto socksSearchDto =
        new SocksSearchDto(color, operation, cottonPart);
    ValidationResult validationResult = ValidationUtils.validate(socksSearchDto);
    if (!validationResult.isValid()) {
      throw new DataValidityException(validationResult.getFirstErrorMessage());
    }
    SocksServiceSearchDto socksServiceSearchDto = SocksMapper.INSTANCE.controllerSearchDtoToServiceDto(socksSearchDto);
    SocksServiceResponse countResponse = sockService.countSocks(socksServiceSearchDto);
    return createResponse(countResponse);
  }

  private ResponseEntity createResponse(ServiceResponse serviceResponse) {
    ResponseDto<Object> responseDto = new ResponseDto<>(
        serviceResponse.getStatus().name(),
        serviceResponse.getPayload()
    );
    responseDto.setMessage(serviceResponse.getMessage());
    switch (serviceResponse.getStatus()) {
      case INTERNAL_ERROR: return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
      case INVALID_DATA: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
      default: return ResponseEntity.ok(responseDto);
    }
  }
}
