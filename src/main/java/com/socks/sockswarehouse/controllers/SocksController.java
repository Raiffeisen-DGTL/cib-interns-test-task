package com.socks.sockswarehouse.controllers;

import com.socks.sockswarehouse.dao.SocksDAO;
import com.socks.sockswarehouse.models.socks.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocksController {

    @Autowired
    private SocksDAO sDAO;

    @PostMapping("/api/socks/income")
    @ResponseBody
    public ResponseEntity<String> income(@RequestParam("color") String color,
                                      @RequestParam("cottonPart") int cottonPart,
                                      @RequestParam("quantity") int quantity) throws Exception {
        Socks newSocks = new Socks(color, cottonPart, quantity);

        try {
            Socks stockSocks = sDAO.findSimilar(newSocks);
            int newQuantity = stockSocks.getQuantity() + newSocks.getQuantity();
            stockSocks.setQuantity(newQuantity);
            sDAO.update(stockSocks);
        }
        catch (EmptyResultDataAccessException e) {
            sDAO.save(newSocks);
        }
        catch(MissingServletRequestParameterException e) {
            return ResponseEntity.status(400)
                    .body("Incorrect request");
        }
        catch (DataAccessException e) {
            return ResponseEntity.status(500)
                    .body("DB writing error");
        }

        return ResponseEntity.status(200)
                .body("Income registered successfully");
    }

    @PostMapping("/api/socks/outcome")
    @ResponseBody
    public ResponseEntity<String> outcome(@RequestParam("color") String color,
                                         @RequestParam("cottonPart") int cottonPart,
                                         @RequestParam("quantity") int quantity) throws Exception {
        Socks newSocks = new Socks(color, cottonPart, quantity);

        try {
            Socks stockSocks = sDAO.findSimilar(newSocks);
            if (stockSocks.getQuantity() < newSocks.getQuantity()) {
                throw new IllegalArgumentException();
            }
            int newQuantity = stockSocks.getQuantity() - newSocks.getQuantity();
            stockSocks.setQuantity(newQuantity);
            sDAO.update(stockSocks);
        }
        catch(IllegalArgumentException | MissingServletRequestParameterException e) {
            return ResponseEntity.status(400)
                    .body("Incorrect request");
        }
        catch (DataAccessException e) {
            return ResponseEntity.status(500)
                    .body("DB writing error");
        }

        return ResponseEntity.status(200)
                .body("Outcome registered successfully");
    }

    @GetMapping("/api/socks")
    @ResponseBody
    public ResponseEntity<String> getInfo(@RequestParam("color") String color,
                                          @RequestParam("operation") String operation,
                                          @RequestParam("cottonPart") int cottonPart) throws Exception {

        List<Socks> socksList;
        int res = 0;

        try {
            socksList = sDAO.findAllByColorAndCottonPartComparison(color, operation, String.valueOf(cottonPart));
            for(Socks socks: socksList) {
                res += socks.getQuantity();
            }
        }
        catch (MissingServletRequestParameterException e) {
            return ResponseEntity.status(400)
                    .body("Incorrect request");
        }
        catch (DataAccessException e) {
            return ResponseEntity.status(500)
                    .body("DB error");
        }

        return ResponseEntity.status(200)
                .body(String.format("%s", res));
    }
}
