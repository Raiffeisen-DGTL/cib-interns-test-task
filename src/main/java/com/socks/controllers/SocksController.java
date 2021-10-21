package com.socks.controllers;

import com.socks.SocksServiceException;
import com.socks.dao.SockDao;
import com.socks.models.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/socks")
public class SocksController {
    private SockDao sockDao;

    @Autowired
    public SocksController(SockDao sockDao) {
        this.sockDao = sockDao;
    }

    @GetMapping
    public int getInfo(@RequestParam String color,
                       @RequestParam String operation,
                       @RequestParam int cottonPart){
         return sockDao.getCountOfSocks(color, operation, cottonPart);
    }

    @PostMapping(path = "/income")
    public void incomeSocks(@RequestBody Sock sock){
        if ((sock.getCottonPart() > 100) || (sock.getCottonPart() < 0) || (sock.getQuantity() <= 0)){
            throw new SocksServiceException();
        } else {
            sockDao.insertOrUpdate(sock);
        }
    }

    @PostMapping (path ="/outcome")
    public void outcomeSocks(@RequestBody Sock sock){
        if ((sock.getCottonPart() > 100) || (sock.getCottonPart() < 0) || (sock.getQuantity() <= 0)){
            throw new SocksServiceException();
        } else {
            sockDao.reduce(sock);
        }
    }
}
