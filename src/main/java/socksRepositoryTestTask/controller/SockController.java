package socksRepositoryTestTask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socksRepositoryTestTask.exception.MissingRequestedQuantityException;
import socksRepositoryTestTask.exception.NoSuchEntityOnDatabaseException;
import socksRepositoryTestTask.model.Color;
import socksRepositoryTestTask.model.SockDTO;
import socksRepositoryTestTask.service.SockServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("api/socks")
public class SockController {
    private final SockServiceImpl socksService;

    public SockController(SockServiceImpl socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    @ResponseBody
    public int socks(@RequestParam Color color,
                     @RequestParam Operation operation,
                     @RequestParam int cottonPart) {
        return socksService.findAndCountSocks(color, operation, cottonPart);
    }

    @PostMapping(path = "income")
    public void income(@Valid @RequestBody SockDTO sockDTO) {
        socksService.income(sockDTO);
    }

    @PostMapping(path = "outcome")
    public void outcome(@Valid @RequestBody SockDTO sockDTO) {
        socksService.outcome(sockDTO);
    }


    public enum Operation {
        lessThan, moreThan, equals
    }

    @ExceptionHandler({NoSuchEntityOnDatabaseException.class, MissingRequestedQuantityException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(RuntimeException e) {
        return new ResponseEntity<>("Not valid due to validation error: " + e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
