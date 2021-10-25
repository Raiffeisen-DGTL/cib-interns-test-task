package com.piminovdmitry.raif_cib_interns_test_task.controller;

import com.piminovdmitry.raif_cib_interns_test_task.entity.StockUnit;
import com.piminovdmitry.raif_cib_interns_test_task.exception_handling.IncorrectRequestException;
import com.piminovdmitry.raif_cib_interns_test_task.exception_handling.NoSufficientStockUnitException;
import com.piminovdmitry.raif_cib_interns_test_task.exception_handling.StockUnitIncorrectData;
import com.piminovdmitry.raif_cib_interns_test_task.service.CompOperation;
import com.piminovdmitry.raif_cib_interns_test_task.service.StockUnitService;
import com.piminovdmitry.raif_cib_interns_test_task.service.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SocksController {

    @Autowired
    private StockUnitService stockUnitService;

    @GetMapping("/socks")
    public int getQuantitySocksUnitByParams(@RequestParam("color") String color,
                                        @RequestParam("operation") String operation,
                                        @RequestParam("cottonPart") int cottonPart) {
        CompOperation compOperation;
        try {
            compOperation = CompOperation.getEnum(operation);
        } catch (Exception e) {
            throw new IncorrectRequestException("Comparison operation is not valid");
        }
        if (cottonPart < 0 || cottonPart > 100) {
            throw new IncorrectRequestException("Cotton part is not range from 0 to 100");
        }
        return stockUnitService.getQuantitySocksUnitByParams(color, cottonPart, compOperation);
    }

    @PostMapping("/socks/income")
    public void addIncome(@RequestBody StockUnit stockUnit) {
        if (stockUnit.IsInvalid()) {
            throw new IncorrectRequestException("Parameters of stock unit is not valid");
        }
        stockUnitService.saveStockUnitMove(stockUnit, TransactionType.INCOME);
    }

    @PostMapping("/socks/outcome")
    public void addOutcome(@RequestBody StockUnit stockUnit) {
        if (stockUnit.IsInvalid()) {
            throw new IncorrectRequestException("Parameters of stock unit is not valid");
        }
        StockUnit savedStockUnit = stockUnitService.saveStockUnitMove(stockUnit, TransactionType.OUTCOME);
        if (savedStockUnit == null) {
            System.out.println("NoSufficientStockUnitException");
            throw new NoSufficientStockUnitException("There is no sufficient socks int the stock");
        }
    }

    @ExceptionHandler
    public ResponseEntity<StockUnitIncorrectData> handleException (IncorrectRequestException exception) {
        StockUnitIncorrectData data = new StockUnitIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<StockUnitIncorrectData> handleException (NoSufficientStockUnitException exception) {
        StockUnitIncorrectData data = new StockUnitIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
