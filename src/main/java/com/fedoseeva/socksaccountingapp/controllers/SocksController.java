package com.fedoseeva.socksaccountingapp.controllers;

import com.fedoseeva.socksaccountingapp.daoimpl.SocksDAOImpl;
import com.fedoseeva.socksaccountingapp.dto.IncomeAndOutcomeDTORequest;
import com.fedoseeva.socksaccountingapp.dto.SelectDTORequest;
import com.fedoseeva.socksaccountingapp.models.Socks;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/socks")
public class SocksController {


    private final SocksDAOImpl socksDAO;

    public SocksController(SocksDAOImpl socksDAO) {
        this.socksDAO = socksDAO;
    }

    @PostMapping(path = "/income", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity income(@Valid @RequestBody IncomeAndOutcomeDTORequest incomeAndOutcomeDTORequest,
                                 BindingResult bindingResult) {

        Socks socks = new Socks(incomeAndOutcomeDTORequest.getColor(), incomeAndOutcomeDTORequest.getCottonPart(),
                incomeAndOutcomeDTORequest.getQuantity());

        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();

        try {
            socksDAO.income(socks);
        } catch (DataAccessException dataAccessException) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();

    }

    @PostMapping(path = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity outcome(@Valid @RequestBody IncomeAndOutcomeDTORequest incomeAndOutcomeDTORequest,
                                  BindingResult bindingResult) {

        Socks socks = new Socks(incomeAndOutcomeDTORequest.getColor(), incomeAndOutcomeDTORequest.getCottonPart(),
                incomeAndOutcomeDTORequest.getQuantity());

        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();

        try {
            socksDAO.outcome(socks);
        } catch (DataAccessException dataAccessException) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();

    }

    @GetMapping()
    public ResponseEntity selection(@ModelAttribute("selection") @Valid SelectDTORequest selectDTORequest,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(socksDAO.select(selectDTORequest.getColor(),
                    selectDTORequest.getOperation(), selectDTORequest.getCottonPart()));
        } catch (DataAccessException dataAccessException) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
