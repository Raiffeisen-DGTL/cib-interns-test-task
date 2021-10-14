package com.example.demo.sock;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.postgresql.util.PSQLException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.net.ConnectException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "api/socks")
@Validated
public class SockController {

    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @GetMapping()
    public Optional getSocks(
            @RequestParam("color") @Pattern(regexp = "^[a-zA-Z]+$") String color,
            @RequestParam("operation") @Pattern(regexp = "^(moreThan)|(lessThan)|(equal)$") @NotNull String operation,
            @RequestParam("cottonPart") @Min(1) @Max(100) @NotNull Byte cottonPart) {
        return sockService.getSocks(color, operation, cottonPart);
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            JsonMappingException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequest(Exception e) {
    }

    @ExceptionHandler({ConnectException.class,PSQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void internalServerError(Exception e) {
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(Exception e) {
    }

    @RequestMapping("/income")
    @PostMapping
    public void registerNewSock(@Valid @RequestBody Sock sock, HttpServletRequest request) {
        sockService.modifySock(sock,request.getRequestURI());
    }

    @RequestMapping("/outcome")
    @PostMapping
    public void removeSock(@Valid @RequestBody Sock sock, HttpServletRequest request) {
        sockService.modifySock(sock,request.getRequestURI());
    }
}
