package com.intern.sock.controller;

import com.intern.sock.enums.OperationEnum;
import com.intern.sock.exceptions.DatabaseException;
import com.intern.sock.exceptions.UrlException;
import com.intern.sock.model.Sock;
import com.intern.sock.service.ServiceSock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class SockController {
    @Autowired
    ServiceSock servicesock;

    @GetMapping(value="api/socks")
    public Long getSocksByColorAndColorPart(@RequestParam(value="color") final String color,
                                            @RequestParam(value="operation") final OperationEnum operation,
                                            @RequestParam(value="cottonPart") final Long cottonPart) throws UrlException, DatabaseException{
        return servicesock.getByColorAndCottonPart(color,operation,cottonPart);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="api/socks/income", method={RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_XML_VALUE)
    public void addSocks(@RequestBody Sock sock) throws UrlException{
        servicesock.addSock(sock);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="api/socks/outcome", method = {RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_XML_VALUE)
    public void reduceSock(@RequestBody Sock sock) throws UrlException{
        servicesock.deleteSock(sock);
    }
}
