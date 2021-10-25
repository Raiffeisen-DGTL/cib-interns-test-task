package appAmirSalyakhov.raiffeisenTask.controller;

import appAmirSalyakhov.raiffeisenTask.advice.SocksControllerExceptionHandler;
import appAmirSalyakhov.raiffeisenTask.model.Response;
import appAmirSalyakhov.raiffeisenTask.model.Socks;
import appAmirSalyakhov.raiffeisenTask.service.socks.SocksService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getSocksByRequestValue(@RequestParam(name = "color") String color,
                                          @RequestParam(name = "operation") String operation,
                                          @RequestParam(name = "cottonPart") Integer cottonPart) {
        return socksService.getSockByColorAndCottonPart(color, operation, cottonPart);
    }

    @PostMapping(value = "/income", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveOrUpdateSocksInWarehouse(@RequestBody Socks socks) {
        ResponseEntity<Response> response;
        try {
            response = socksService.saveOrUpdateSocksQuantityFormWarehouse(socks);
        } catch (SocksControllerExceptionHandler ex) {
            return new SocksControllerExceptionHandler().handleNotReadableException();
        }
        return response;
    }

    @PostMapping(value = "/outcome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> subtractSocksFromWarehouse(@RequestBody Socks socks) {
        ResponseEntity<Response> response;
        try {
            response = socksService.subtractSocksFormWarehouse(socks);
        } catch (SocksControllerExceptionHandler ex) {
            return new SocksControllerExceptionHandler().handleNotReadableException();
        }
        return response;
    }
}
