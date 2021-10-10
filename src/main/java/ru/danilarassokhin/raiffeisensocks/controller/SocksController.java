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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.apidocs.ErrorResponse;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.exception.InternalException;
import ru.danilarassokhin.raiffeisensocks.object.ValidationResult;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;
import ru.danilarassokhin.raiffeisensocks.util.ValidationUtils;

import javax.validation.ConstraintViolationException;

import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.SOCKS;

/**
 * Controller for operations with {@link ru.danilarassokhin.raiffeisensocks.model.Socks}
 */
@Api(tags = "Socks controller")
@RestController
@RequestMapping(API_ENDPOINT + SOCKS.ENDPOINT)
public class SocksController {

    private final SocksService sockService;

    @Autowired
    public SocksController(SocksService sockService) {
        this.sockService = sockService;
    }

    /**
     * Adds new socks to stock
     * @param socksIncomeDto Income data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto}
     * @return Response with current quantity of given socks color and cotton part
     * @throws DataValidityException If request data is not valid
     * @throws InternalException If internal exception occurred
     */
    @ApiOperation(value = "Income socks to stock")
    @Operation(
            summary = "Income socks to stock",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful income",
                            content = {
                                    @Content(mediaType = "application/json"
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
    @PostMapping(SOCKS.INCOME)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<SocksIncomeDto> incomeSocks(
            @Parameter(name = "SocksIncomeDto", description = "Socks income information", required = true)
            @RequestBody SocksIncomeDto socksIncomeDto)
            throws DataValidityException, InternalException {
        ValidationResult validationResult = ValidationUtils.validate(socksIncomeDto);
        if(!validationResult.isValid()) {
            throw new DataValidityException(validationResult.getFirstErrorMessage());
        }
        return new ResponseDto<>("Success",
                sockService.income(socksIncomeDto)
        );
    }

    /**
     * Removes some socks from stock
     * @param socksOutcomeDto Outcome data {@link ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto}
     * @return Response with current quantity of given socks color and cotton part
     * @throws DataValidityException If request data is not valid
     * @throws DataNotExistsException If socks of given color and cotton part is not exists
     * @throws InternalException If internal exception occurredd
     */
    @ApiOperation(value = "Outcome socks from stock")
    @Operation(
            summary = "Outcome socks from stock",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful outcome",
                            content = {
                                    @Content(mediaType = "application/json"
                                    )
                            }),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request. Some values were wrong or there is not enough socks in stock left",
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
    @PostMapping(SOCKS.OUTCOME)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<SocksOutcomeDto> outcomeSocks(
            @ApiParam(name = "SocksOutcomeDto", value = "Socks outcome information", required = true)
            @RequestBody SocksOutcomeDto socksOutcomeDto)
            throws DataValidityException, DataNotExistsException, InternalException {
        ValidationResult validationResult = ValidationUtils.validate(socksOutcomeDto);
        if(!validationResult.isValid()) {
            throw new DataValidityException(validationResult.getFirstErrorMessage());
        }
        return new ResponseDto<>("Success",
                sockService.outcome(socksOutcomeDto)
        );
    }

    /**
     * Counts socks of given color and cotton part condition
     * @param color Socks color to count
     * @param operation Condition to use
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
                                    @Content(mediaType = "application/json"
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Long> countSocks(
            @ApiParam(name = "color", value = "Socks color to count", required = true)
                @RequestParam("color") String color,
            @ApiParam(name = "operation", value = "Operation for cotton part equality check", required = true)
                @RequestParam("operation") String operation,
            @ApiParam(name = "cottonPart", value = "Cotton part to use in equality check", required = true)
                @RequestParam("cottonPart") byte cottonPart)
            throws DataValidityException {
        SocksSearchDto socksSearchDto = new SocksSearchDto();
        socksSearchDto.setColor(color);
        socksSearchDto.setCottonPart(cottonPart);
        socksSearchDto.setOperation(operation);
        ValidationResult validationResult = ValidationUtils.validate(socksSearchDto);
        if(!validationResult.isValid()) {
            throw new DataValidityException(validationResult.getFirstErrorMessage());
        }
        return new ResponseDto<>("Success",
                sockService.countSocks(socksSearchDto)
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseDto<>("Constraint violation error", exception.getMessage());
    }

    @ExceptionHandler(DataValidityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleDataValidityException(DataValidityException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ResponseDto<>("Error", "Check your request data. May be it's null or empty? Well, it shouldn't be");
    }

    @ExceptionHandler(DataNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleDataNotExistsException(DataNotExistsException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<String> handleInternalException(InternalException exception) {
        return new ResponseDto<>("Internal error", exception.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleRequestParameterException(MissingServletRequestParameterException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

}
