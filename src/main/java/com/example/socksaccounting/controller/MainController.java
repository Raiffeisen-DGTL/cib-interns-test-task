package com.example.socksaccounting.controller;

import com.example.socksaccounting.Operation;
import com.example.socksaccounting.Socks;
import com.example.socksaccounting.SocksRepository;
import com.example.socksaccounting.exception.SocksNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends Exception {
    private final SocksRepository socksRepository;

    public MainController(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @GetMapping(value = "/api/socks/all")
    public @ResponseBody Iterable<Socks> getSocks(){
        return socksRepository.findAll();
    }

    @GetMapping(value = "/api/socks")
    public @ResponseBody Integer getSocks(@RequestParam(value = "color") String color,
                                                  @RequestParam(value = "operation") Operation operation,
                                                  @RequestParam(value = "cottonPart") Byte cottonPart){
        Iterable<Socks> socks;

        if (operation == Operation.MoreThan){
            socks = socksRepository.findByColorAndCottonPartMoreThan(color, cottonPart);
        } else if (operation == Operation.LessThan){
            socks = socksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
        } else {
            socks = socksRepository.findByColorAndCottonPartEqual(color, cottonPart);
        }

        int quantity = 0;
        for(Socks sock: socks){
            quantity += sock.getQuantity();
        }
        return quantity;
    }

    @GetMapping(value = "/api/socks/income")
    public @ResponseBody String socksIncome(@RequestParam(value = "color") String color,
                              @RequestParam(value = "cottonPart") Byte cottonPart,
                              @RequestParam(value = "quantity") Integer quantity){

        Socks socks = socksRepository.findByColorAndCottonPart(color, cottonPart);
        if (socks != null){
            socks.setQuantity(socks.getQuantity() + quantity);
            socksRepository.save(socks);
            return "Socks are successfully added(increased quantity)";
        } else {
            socks = new Socks(color, cottonPart, quantity);
            socksRepository.save(socks);
            return "Socks are successfully added";
        }
    }

    @GetMapping(value = "/api/socks/outcome")
    public @ResponseBody String socksOutcome(@RequestParam String color,
                               @RequestParam Byte cottonPart,
                               @RequestParam Integer quantity) throws SocksNotFoundException {
        Socks socks = socksRepository.findByColorAndCottonPart(color, cottonPart);
        if (socks != null && socks.getQuantity() > quantity){
            socks.setQuantity(socks.getQuantity() - quantity);
            socksRepository.save(socks);
            return "Socks are successfully removed(reduced)";
        } else {
            throw new SocksNotFoundException();
        }
    }
}
