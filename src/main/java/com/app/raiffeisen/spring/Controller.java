package com.app.raiffeisen.spring;

import com.app.raiffeisen.database.DataBaseController;
import com.app.raiffeisen.socks.SocksData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    @ResponseBody
    @ResponseStatus
    @RequestMapping("/api/socks/income")
    public ResponseEntity<String> income(@RequestBody SocksData socksData) {
        System.out.println("asaf" + socksData);
        return DataBaseController.getInstance().changeSocksCount(socksData);
    }

    @ResponseBody
    @ResponseStatus
    @RequestMapping("/api/socks/outcome")
    public ResponseEntity<String> outcome(@RequestBody SocksData socksData) {
        socksData.setOutcome();
        return DataBaseController.getInstance().changeSocksCount(socksData);
    }

    @ResponseStatus
    @GetMapping("/api/socks")
    public ResponseEntity<String> socks(HttpServletRequest request) {
        return DataBaseController.getInstance().countSocks(
                request.getParameter("color"),
                request.getParameter("operation"),
                request.getParameter("cottonPart")
        );
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
