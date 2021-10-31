package dawin.york.rafftest.socks;


import dawin.york.rafftest.socks.exceptions.InvalidParameterException;
import dawin.york.rafftest.socks.exceptions.SocksExceptionHandler;
import dawin.york.rafftest.socks.tos.OperationType;
import dawin.york.rafftest.socks.tos.Socks;
import dawin.york.rafftest.socks.tos.SocksId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/socks")
@SocksExceptionHandler
@Slf4j
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping(value = "/income")
    public void income(@RequestBody Socks socks) {
        log.debug(socks.toString());
        if (socks.isValid())
            socksService.incomeSocks(socks);
        else {
            throw new InvalidParameterException("Income parameters is invalid");
        }
    }

    @PostMapping(value = "/outcome")
    public void outcome(@RequestBody Socks socks) {
        if (socks.isValid())
            socksService.outcomeSocks(socks);
        else {
            throw new InvalidParameterException("Outcome parameters is invalid");
        }
    }

    @GetMapping
    public int getQuantity(@RequestParam("color") String color,
                           @RequestParam("operation") OperationType operation,
                           @RequestParam("cottonPart") int cottonPart) {
        SocksId checkValid = new SocksId(color, cottonPart);
        if (checkValid.isValid())
            return Optional.ofNullable(socksService.getQuantity(checkValid, operation))
                    .orElse(0);
        else {
            throw new InvalidParameterException("Get parameters is invalid");
        }
    }
}
