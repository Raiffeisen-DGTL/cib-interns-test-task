package com.kurtlike.rbank;

import com.kurtlike.rbank.db.SocksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping ("api")
public class SocksController {
    @Autowired
    SocksServiceImpl socksRepoCustom;
    @PostMapping(value = "/socks/income")
    private void addSocks(@RequestBody SocksParcel socksParcel, HttpServletResponse response){
        int quantity = socksParcel.quantity;
        String color = socksParcel.color;
        int cottonPart = socksParcel.cottonPart;
        if( quantity < 0 ||  cottonPart < 0 || cottonPart > 100 || color.equals("")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else{
            socksRepoCustom.addSocks(color, cottonPart, quantity);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
    @PostMapping(value = "/socks/outcome")
    private String getSocks(@RequestBody SocksParcel socksParcel, HttpServletResponse response){
        int quantity = socksParcel.quantity;
        String color = socksParcel.color;
        int cottonPart = socksParcel.cottonPart;
        if( quantity < 0 ||  cottonPart < 0 || cottonPart > 100 || color.equals("")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "wrong input";
        }else{
            if(socksRepoCustom.getSocks(color, cottonPart, quantity) == 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                return "Ok";
            }else return "Not enough quantity";
        }
    }
    @GetMapping("/socks")
    private long getSocksQuantity(@RequestParam String color, String operation, int cottonPart, HttpServletResponse response){
        if(cottonPart < 0 || cottonPart > 100 || color.equals("")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            return  socksRepoCustom.getQuantity(color,operation,cottonPart);
        }
    }
}
