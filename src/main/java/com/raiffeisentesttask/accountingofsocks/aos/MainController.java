package com.raiffeisentesttask.accountingofsocks.aos;

import com.raiffeisentesttask.accountingofsocks.aos.entity.Response;
import com.raiffeisentesttask.accountingofsocks.aos.entity.ResponseForGetRequest;
import com.raiffeisentesttask.accountingofsocks.aos.entity.Socks;
import com.raiffeisentesttask.accountingofsocks.aos.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/socks")
public class MainController {

    private final Response bedRequestResponse = new Response("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат");
    private final Response okResponse = new Response("HTTP 200 — удалось добавить приход");

    @Autowired
    private SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity<Response> addSocks(@Valid @RequestBody Socks socks, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bedRequestResponse, HttpStatus.BAD_REQUEST);
        }

        socksService.saveOrUpdateSocks(socks);

        return new ResponseEntity<>(okResponse, HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<Response> deleteSocks(@Valid @RequestBody Socks socks, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bedRequestResponse, HttpStatus.BAD_REQUEST);
        }

        if (socksService.deleteSocks(socks)){
            return new ResponseEntity<>(okResponse, HttpStatus.OK);
        } else return new ResponseEntity<>(bedRequestResponse, HttpStatus.BAD_REQUEST);

    }

    @GetMapping
    public ResponseEntity<Response> greeting(@RequestParam(name="color") String color,
                                          @RequestParam(name="operation") String operation,
                                          @RequestParam(name="cottonPart") int cottonPart) {

        int socksCounter = socksService.findTheNumberOfMatchingSocks(color, operation, cottonPart);
//        Response response = new Response("HTTP 200 — запрос выполнен.", String.valueOf(socksCounter));
//        return new ResponseEntity<>(response, HttpStatus.OK);
        ResponseForGetRequest response = new ResponseForGetRequest("HTTP 200 — запрос выполнен.", String.valueOf(socksCounter));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
