package com.github.el_hopaness_romtic.test_task.api;

import com.github.el_hopaness_romtic.test_task.model.SocksBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/socks")
public class SocksBatchController {

    @Autowired
    private SocksBatchService SocksBatchService;

    @Autowired
    private Validator validator;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @PostMapping("/income")
    SocksBatch income(@Valid @RequestBody SocksBatch socks) {
        return SocksBatchService.addBatch(socks);
    }

    @PostMapping("/outcome")
    SocksBatch outcome(@Valid @RequestBody SocksBatch socks) {
        return SocksBatchService.removeBatch(socks);
    }

    @GetMapping
    int getQuantityByColorAndCottonPart(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam Integer cottonPart
    ) {
        // verifying parameters
        SocksBatch test = new SocksBatch(color, cottonPart, 1);
        var violations = validator.validate(test);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(String.join(
                    ", ",
                    violations.stream().map(ConstraintViolation::getMessage).toArray(String[]::new)
            ));
        }

        switch (operation) {
            case "moreThan":
                return SocksBatchService.countQuantityByColorAndCottonPartMoreThan(color, cottonPart);
            case "lessThan":
                return SocksBatchService.countQuantityByColorAndCottonPartLessThan(color, cottonPart);
            case "equal":
                return SocksBatchService.countQuantityByColorAndCottonPart(color, cottonPart);
            default:
                throw new IllegalArgumentException("Invalid operation, should be \"lessThan\", \"moreThan\" or \"equal\"");
        }
    }
}
