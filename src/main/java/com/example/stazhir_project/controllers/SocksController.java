package com.example.stazhir_project.controllers;

import com.example.stazhir_project.model.Socks;
import com.example.stazhir_project.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    public String hello(){
        return "hello";
    }

    @GetMapping("/wareHouse")
    public String wareHouse(Model model){
        model.addAttribute("socksList", socksService.findAll());
        return "wareHouse";
    }

    @GetMapping("/api/socks/income")
    public String socksIncomeForm(Socks socks){
        return "socks-income-form";
    }

    @PostMapping("/api/socks/income")
    public String socksIncome(Socks socks){
        List<Socks> list = socksService.findAll();
        int id = -1;
        for (Socks s : list){
            if (s.getColor().equals(socks.getColor()) && s.getCottonPart() == socks.getCottonPart()){
                id = s.getId();
                break;
            }
        }
        if (id != -1){
            Socks socksPr = socksService.findById(id);
            socksPr.setQuantity(socksPr.getQuantity() + socks.getQuantity());
            socksService.saveSocks(socksPr);
        }
        else {
            socksService.saveSocks(socks);
        }
        return "redirect:/wareHouse";
    }


    @GetMapping("/api/socks/outcome")
    public String socksOutcomeForm(Socks socks){
        return "socks-outcome-form";
    }

    @PostMapping("/api/socks/outcome")
    public String socksOutcome(Socks socks, Model model){
        List<Socks> list = socksService.findAll();
        int id = -1;
        for (Socks s : list){
            if (s.getColor().equals(socks.getColor()) && s.getCottonPart() == socks.getCottonPart()){
                id = s.getId();
                break;
            }
        }
        if (id != -1){
            Socks socksPr = socksService.findById(id);
            if(socksPr.getQuantity() - socks.getQuantity() >= 0) {
                socksPr.setQuantity(socksPr.getQuantity() - socks.getQuantity());
                socksService.saveSocks(socksPr);
            }
            else {
                model.addAttribute("countZapr", socks.getQuantity());
                model.addAttribute("countInWarehouse", socksPr.getQuantity());
                return "socks-outcome-form-notEnough";
            }


        }
        else {
            return "socks-outcome-form-notFromTheWarehouse";
        }
        return "redirect:/wareHouse";
    }

    @GetMapping("/api/socks")
    public String amountSocks(@RequestParam(value = "color") String color,
                              @RequestParam(value = "operation") String operation,
                              @RequestParam(value = "cottonPart") String cottonPart,
                              Model model){
        model.addAttribute("color", color);
        model.addAttribute("operation", operation);
        model.addAttribute("cottonPart", cottonPart);
        List<Socks> list = null;
        if(operation.equals("moreThan")){
            list = socksService.moreThan(Integer.parseInt(cottonPart), color);
        } else if(operation.equals("lessThan")){
            list = socksService.lessThan(Integer.parseInt(cottonPart), color);
        } else {
            list = socksService.equal(Integer.parseInt(cottonPart), color);
        }
        int count = 0;
        for (Socks s : list){
           count += s.getQuantity();
        }

        model.addAttribute("count", count);

        return "countSocks";
    }
}
