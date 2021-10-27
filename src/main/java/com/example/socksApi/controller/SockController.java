package com.example.socksApi.controller;

import com.example.socksApi.dto.FindCountResponse;
import com.example.socksApi.dto.IncomeDto;
import com.example.socksApi.dto.OutcomeDto;
import com.example.socksApi.dto.SockResponse;
import com.example.socksApi.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

import static com.example.socksApi.resources.SwaggerResources.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Tag(name = SOCK_SERVICE, description = SOCK_SERVICE_DESC)
public class SockController {

    private final SockService sockService;

    @Operation(summary = INCOME_POST,
            description = INCOME_POST_DESC,
            responses = {
                    @ApiResponse(description = "Success response",
                            responseCode = "200",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SockResponse.class))),
                    @ApiResponse(description = "Validation error",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class))),
                    @ApiResponse(description = "Internal server error",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class)))})
    @PostMapping("/income")
    public ResponseEntity<SockResponse> income(@Valid @RequestBody IncomeDto dto){
        sockService.income(dto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new SockResponse(true, LocalDateTime.now(), "OK"));
    }

    @Operation(summary = OUTCOME_POST,
            description = OUTCOME_POST_DESC,
            responses = {
                    @ApiResponse(description = "Success response",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class))),
                    @ApiResponse(description = "Validation error",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class))),
                    @ApiResponse(description = "Internal server error",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class)))})
    @PostMapping("/outcome")
    public ResponseEntity<SockResponse> outcome(@RequestBody @Valid OutcomeDto dto){
        sockService.outcome(dto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new SockResponse(true, LocalDateTime.now(), "OK"));
    }

    @Operation(summary = FIND_COUNT_POST,
            description = FIND_COUNT_POST_DESC,
            responses = {
                    @ApiResponse(description = "Success response",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = FindCountResponse.class))),
                    @ApiResponse(description = "Validation error",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class))),
                    @ApiResponse(description = "Internal server error",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json;charset=utf-8",
                                    schema = @Schema(implementation = SockResponse.class)))})
    @GetMapping(produces = "application/json;charset=utf-8")
    private ResponseEntity<FindCountResponse> findCount(@RequestParam("color") String color,
                                     @RequestParam("operation") String operation,
                                     @Valid @RequestParam("cottonPart") @Min(0) @Max(100) int cottonPart) {
        int count = sockService.count(color, operation, cottonPart);
        return ResponseEntity.status(HttpStatus.OK).body(
                new FindCountResponse(true, LocalDateTime.now(), "OK", count));
    }

}
