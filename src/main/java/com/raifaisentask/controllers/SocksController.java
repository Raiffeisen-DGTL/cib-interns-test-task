package com.raifaisentask.controllers;

import com.raifaisentask.SocksDao;
import com.raifaisentask.data.BadRequestException;
import com.raifaisentask.data.Socks;
import com.raifaisentask.data.SocksCompareOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocksController {
    SocksDao socksDao;

    @Autowired
    public SocksController(SocksDao socksDao) {
        this.socksDao = socksDao;
    }

    @GetMapping("/api/socks")
    public Long getSocks(@RequestParam(value = "color", required = false) String color,
                         @RequestParam(value = "operation", required = false) String operationStr,
                         @RequestParam(value = "cottonPart", required = false) Integer cottonPart)
            throws NoSuchFieldException, BadRequestException {
        String operationSign;
        if (color == null) {
            if (operationStr == null || cottonPart == null) {
                throw new BadRequestException("Отсутствуют параметры отбора!");
            } else {
                operationSign = SocksCompareOperation.signFromString(operationStr);
                return socksDao.getSocksQuantityByCottonPart(operationSign, cottonPart);
            }
        } else {
            if (operationStr == null || cottonPart == null) {
                return socksDao.getSocksQuantityByColor(color);
            } else {
                operationSign = SocksCompareOperation.signFromString(operationStr);
                return socksDao.getSocksQuantityByColorAndCottonPart(color, operationSign, cottonPart);
            }
        }
    }

    @PostMapping(value = "/api/socks/income", consumes = "application/json")
    public void incomeSocks(@RequestBody Socks socks) throws BadRequestException {
        socksDao.addSocks(socks.getColor(),socks.getCottonPart(), socks.getQuantity());
    }

    @PostMapping(value = "/api/socks/outcome", consumes = "application/json")
    public void outcomeSocks(@RequestBody Socks socks) throws BadRequestException {
        socksDao.removeSocks(socks.getColor(),socks.getCottonPart(), socks.getQuantity());
    }

}
