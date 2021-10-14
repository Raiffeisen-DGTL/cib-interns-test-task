package com.korolev.rest_raif.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.korolev.rest_raif.domain.Sock;
import com.korolev.rest_raif.repository.SocksRepos;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api/socks")
public class RequestController {
    @Autowired
    private SocksRepos socksRepos;

    // запрос на добавление прихода носков
    @PostMapping("income")
    @ResponseBody
    public void saveData(@RequestBody ObjectNode json){
        System.out.println(json.get("color").asText());
        socksRepos.save(new Sock(json.get("color").asText(),json.get("cottonPart").intValue(),json.get("quantity").intValue(),true));
    }

    // запрос на выдачу носков
    @PostMapping("outcome")
    public void outcome(@RequestBody ObjectNode json) throws NotFoundException {
        String color = json.get("color").asText();
        Integer quantity = json.get("quantity").intValue();
        Integer cottonPart = json.get("cottonPart").intValue();
        // Проверка возможности списания запрашиваемого количества носков(проверяем, чтобы не получить отрицательный остаток)
        if(socksRepos.findBalanceBeforeOutcome(color,cottonPart) - quantity < 0){
            throw new NotFoundException("Incorrect quantity");
        }
        socksRepos.save(new Sock(color,cottonPart,quantity,false));
    }

    //запрос на получение остатка
    @GetMapping()
    public @ResponseBody Integer getSocks(@RequestParam(value="color") String color,
                                          @RequestParam(value="operation") String operation,
                                          @RequestParam(value="cottonPart") Integer cottonPart) {
        Integer result = socksRepos.findBalance(color,operation,cottonPart);
               if(result == null) result = 0;
        return result;
    }
}
