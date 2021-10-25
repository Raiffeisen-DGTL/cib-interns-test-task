package ru.simbial.cibinternstesttask.app;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.simbial.cibinternstesttask.app.model.SocksRequestData;

@RestController
@RequestMapping("/socks")
public class SocksController {
    private final SocksService service;

    public SocksController(SocksService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> getSocksCountByFilter(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam Integer cottonPart) {

       return service.countSocksByFilter(color, operation, cottonPart);
    }

    @PostMapping(value = "/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSocks(@RequestBody /*@Valid*/ SocksRequestData data) {
        return service.registerSocksIncome(data);
    }

    @PostMapping(value = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> withdrawSocks(@RequestBody /*@Valid */SocksRequestData data) {
        return service.registerSocksOutcome(data);
    }

}
