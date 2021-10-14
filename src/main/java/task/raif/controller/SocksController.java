package task.raif.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.raif.enumContainer.Operations;
import task.raif.exception.SocksValidationException;
import task.raif.model.SocksFilter;
import task.raif.model.SocksLot;
import task.raif.model.SocksStorage;
import task.raif.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService service;

    public SocksController(SocksService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<SocksLot> getAll() {
        return service.getAll();
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello from Socks Storage";
    }

    @GetMapping
    public long get(@RequestParam @NotBlank String color, @RequestParam @NotNull @Min(0) @Max(100) Integer cottonPart,
            @RequestParam(required = false) String operation) {
        return service.get(new SocksFilter(color, cottonPart, parseOperation(operation)));
    }

    @PostMapping("/income")
    public SocksLot put(@RequestBody @Valid SocksLot lot) {
        return service.put(lot);
    }

    @PostMapping("/outcome")
    public SocksLot take(@RequestBody @Valid SocksLot lot) {
        return service.take(lot);
    }

    private Operations parseOperation(String s) {
        return s != null ? Operations.byTitle(s)
                                     .orElseThrow(() -> new SocksValidationException(
                                             "Operation" + s + " not supported"))
                         : Operations.EQUAL;
    }

}
