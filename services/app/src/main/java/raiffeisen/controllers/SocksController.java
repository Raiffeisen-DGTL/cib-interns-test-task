package raiffeisen.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;
import raiffeisen.models.socks.Socks;
import raiffeisen.services.SocksService;
import raiffeisen.utils.CheckParam;
import raiffeisen.utils.Operator;
import raiffeisen.utils.Response;

/**
 * @author voroningg
 */
@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private static final Logger logger = LoggerFactory.getLogger(SocksController.class);
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/create_table")
    public ResponseEntity<String> createTable() {
        try {
            socksService.createTable();
            return Response.ok();
        } catch (BadSqlGrammarException ex) {
            logException(ex);
            return Response.conflict();
        } catch (Exception ex) {
            logException(ex);
            return Response.internalServerError();
        }
    }

    @PostMapping("/income")
    public ResponseEntity<String> income(@RequestBody Socks socks) {
        try {
            if (isInvalidSocks(socks)) {
                return Response.badRequest();
            }
            socksService.income(socks);
            return Response.ok();
        } catch (Exception ex) {
            logException(ex);
            return Response.internalServerError();
        }
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> outcome(@RequestBody Socks socks) {
        try {
            if (isInvalidSocks(socks)) {
                return Response.badRequest();
            }
            socksService.outcome(socks);
            return Response.ok();
        } catch (IllegalArgumentException ex) {
            logException(ex);
            return Response.badRequest();
        } catch (Exception ex) {
            logException(ex);
            return Response.internalServerError();
        }
    }

    @GetMapping
    public ResponseEntity<String> getSocksCount(
            @RequestParam String color,
            @RequestParam("operation") String operator,
            @RequestParam int cottonPart) {
        try {
            if (!CheckParam.isExistingOperator(operator) || CheckParam.isNullOrEmpty(color)) {
                return Response.badRequest();
            }
            Integer count = socksService.countFiltered(color, Operator.parse(operator), cottonPart);
            return Response.ok(String.valueOf(count));
        } catch (Exception ex) {
            logException(ex);
            return Response.internalServerError();
        }
    }

    private boolean isInvalidSocks(Socks socks) {
        return CheckParam.isLessThan(socks.getQuantity(), 0)
                || CheckParam.isNotInRange(socks.getCottonPart(), 0, 100)
                || CheckParam.isNullOrEmpty(socks.getColor());
    }

    private void logException(Exception ex) {
        logger.error(ex.getMessage());
    }
}
