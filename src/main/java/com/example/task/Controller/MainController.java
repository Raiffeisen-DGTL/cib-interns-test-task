package com.example.task.Controller;

import com.example.task.Other.SockDAOimpl;
import com.example.task.Other.Socks;
import com.example.task.Service.SocksService;
import com.example.task.Specification.SocksSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@Controller
public class MainController {


    private SocksService socksService;

    private SockDAOimpl sockDAOimpl;

    @Autowired
    public void setSockDAOimpl(SockDAOimpl sockDAOimpl) {
        this.sockDAOimpl = sockDAOimpl;
    }

    @Autowired
    public void setSocksService(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(value = "word", required = false)String word,
                          @RequestParam(value = "min", required = false) Integer min,
                          @RequestParam(value = "max", required = false) Integer max

    ){

        Specification<Socks> filter = Specification.where(null);

        if (word != null){
            filter = filter.and(SocksSpecification.contains(word));
        }
        if (min != null){
            filter = filter.and(SocksSpecification.cottonPartLessorEq(min));
        }
        if (max != null){
            filter = filter.and(SocksSpecification.cottonPartGreaterorEq(max));
        }
//        filter = filter.and(SocksSpecification.cottonPartLessorEq(30));
        List<Socks> resList = socksService.getSocksWithPagingAndFiltering(filter, PageRequest.of(0,50)).getContent();
        model.addAttribute("socks", resList);
        return "socks";
    }

    @GetMapping("/index")
    public String doSomething() {
        return "index";
    }

    @GetMapping("/income")
    public String showAddSockForm(Model model) {
        Socks socks = new Socks(null, null, null,null);
        model.addAttribute("socks", socks);
        return "sock-form";
    }

    @PostMapping("/income")
//    @ResponseBody
    public String showAddSockForm(@ModelAttribute(value = "socks") Socks socks) {
        if ((socks.getCottonPart() > 100) || (socks.getCottonPart()<0)){
            System.out.println("Неверный ввод числа");
            return "redirect:/index";
        }

        System.out.println(socks.getId() + " " + socks.getColor()+ " " + socks.getCottonPart()+ " " + socks.getQuantity());
        Socks sockAdd = new Socks(socks.getId(),socks.getColor(),socks.getCottonPart(),socks.getQuantity());
        socksService.save(sockAdd);
        return "redirect:/index";
    }

    @GetMapping("/outcome")
    public String deletesock(Model model) {
        Socks socks = new Socks(null, null, null, null);
        model.addAttribute("socks", socks);
        return "sock-form-outcome";
    }

    @PostMapping("/outcome")
    public String deletesock(@ModelAttribute(value = "socks") Socks socks) {
        System.out.println(socks.getId() + " " + socks.getColor()+ " " + socks.getCottonPart()+ " " + socks.getQuantity());

        Socks sockAdd = new Socks(socks.getId(),socks.getColor(),socks.getCottonPart(), - socks.getQuantity());

        socksService.save(sockAdd);
        return "redirect:/index";
    }
}
