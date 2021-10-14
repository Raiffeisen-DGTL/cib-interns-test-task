package com.example.socksmanager.controller;

import com.example.socksmanager.db.socks.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksCountController {

    @Autowired
    private SocksService socksService;

    @GetMapping
    public ResponseEntity<Object> getSocksCount(@RequestParam @NotBlank @Size(max = 20) String color,
                                                @RequestParam @NotBlank @Size(max = 10) String operation,
                                                @RequestParam @Min(0) @Max(100) int cottonPart) {
        ResponseEntity<Object> response;
        if (!List.of("moreThan", "lessThan", "equal").contains(operation.trim())) {
            response = new ResponseEntity<>("operation должно принимать одно из значений: moreThan, lessThan или equal",
                    HttpStatus.BAD_REQUEST);
        } else {
            int count = socksService.getSocksCount(color, operation, cottonPart);
            response = new ResponseEntity<>("Носков есть " + count, HttpStatus.OK);
        }
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            message.append(((FieldError) error).getField()).append(" - ").append(error.getDefaultMessage()).append("\n");
        });
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultExceptions(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
