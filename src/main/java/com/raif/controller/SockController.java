package com.raif.controller;

import com.raif.model.Sock;
import com.raif.repository.SockRepository;
import com.raif.service.SockService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class SockController {
    @Autowired
    private SockRepository sockRepository;

    @Autowired
    private SockService sockService;

    @PostMapping("/api/socks/income")
    public ResponseEntity<String> income(HttpServletRequest request) {
        try{
            try{
                sockService.IncomeAndOutcomeParamsCheck(request);
            }
            catch (Exception ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("HTTP Response: 400\n" + "Wrong request params");
            }

            String color = request.getParameter("color");
            Integer cottonPart = Integer.parseInt(request.getParameter("cottonPart"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));

            for(int i = 0; i < quantity; i++){
                sockRepository.save(new Sock(color, cottonPart));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body("HTTP Response: 200\n" + "Request successfully processed");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("HTTP Response: 500\n" + "Internal server error");
        }

    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<String> outcome(HttpServletRequest request) {
        try{
            try{
                sockService.IncomeAndOutcomeParamsCheck(request);
            }
            catch (Exception ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("HTTP Response: 400\n" + "Wrong request params");
            }

            String color = request.getParameter("color");
            Integer cottonPart = Integer.parseInt(request.getParameter("cottonPart"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));

            // getting all sock with given parameters from db
            ArrayList<Sock> lst = sockRepository.findAllByColorAndCottonPart(color, cottonPart);

            // if we don't have enough socks with given parameters we will return HTTP 400
            if(lst.size() < quantity){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("HTTP Response: 400\n" + "Wrong request params");
            }

            for(int i = 0; i < quantity; i++){
                sockRepository.deleteById(lst.get(i).getId());
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body("HTTP Response: 200\n" + "Request successfully processed");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("HTTP Response: 500\n" + "Internal server error");
        }
    }

    @GetMapping("/api/socks")
    public ResponseEntity<String> index(HttpServletRequest request) {
        try{
            try{
                sockService.ParamsCheck(request);
            }
            catch (Exception ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("HTTP Response: 400\n" + "Wrong request params");
            }

            String color = request.getParameter("color");
            Integer cottonPart = Integer.parseInt(request.getParameter("cottonPart"));
            String operation = request.getParameter("operation");

            if(operation.equals("moreThan")){

                int count = sockRepository.findAllByColorAndMoreThanCottonPart(color, cottonPart).size();

                return ResponseEntity.status(HttpStatus.OK)
                        .body("HTTP Response: 200\n" + "count: " + count + "\n" +  "Request successfully processed");
            }
            else if (operation.equals("lessThan")){

                int count = sockRepository.findAllByColorAndLessThanCottonPart(color, cottonPart).size();

                return ResponseEntity.status(HttpStatus.OK)
                        .body("HTTP Response: 200\n" + "count: " + count + "\n" +  "Request successfully processed");
            }
            else if (operation.equals("equals")){

            int count = sockRepository.findAllByColorAndCottonPart(color, cottonPart).size();

            return ResponseEntity.status(HttpStatus.OK)
                    .body("HTTP Response: 200\n" + "count: " + count + "\n" +  "Request successfully processed");
            }
            else {
                throw new Exception();
            }

        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("HTTP Response: 500\n" + "Internal server error");
        }
    }

}