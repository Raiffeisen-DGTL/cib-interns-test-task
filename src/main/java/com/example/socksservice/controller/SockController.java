package com.example.socksservice.controller;

import com.example.socksservice.model.Sock;
import com.example.socksservice.service.SockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @ApiOperation(value = "Registers the arrival of socks at the warehouse")
    @ApiResponses(value={
            @ApiResponse(code = 200, message ="Managed to add an income"),
            @ApiResponse(code=400, message="The request parameters are missing or have an incorrect format"),
            @ApiResponse(code=500, message ="Error on the server")
    }
    )
    @PostMapping("/income")
    public HttpStatus income(@RequestBody Sock sock){
        sockService.income(sock);
        return  HttpStatus.OK;
    }


    @ApiOperation(value = "Registers the release of socks from the warehouse")
    @ApiResponses(value={
            @ApiResponse(code = 200, message ="Managed to add an outcome"),
            @ApiResponse(code=400, message="The request parameters are missing or have an incorrect format"),
            @ApiResponse(code=500, message ="Error on the server")
    })
    @PostMapping("/outcome")
    public HttpStatus outcome(@RequestBody Sock sock){
        sockService.outcome(sock);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Returns the total number of socks in stock that match the request criteria passed in the parameters")
    @ApiResponses(value={
            @ApiResponse(code = 200, message ="The request is executed, the result is in the response body as a string representation of an integer"),
            @ApiResponse(code=400, message="The request parameters are missing or have an incorrect format"),
            @ApiResponse(code=500, message ="Error on the server")
    })
    @GetMapping()
    public int findCount(@RequestParam("color")String color,
                         @RequestParam("operation")String operation,
                         @RequestParam("cottonPart")int cottonPart
                         ){
        return sockService.count(color,operation,cottonPart);
    }


}
