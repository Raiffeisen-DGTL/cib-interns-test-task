package com.example.interntask;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.javatuples.*;
import java.util.Objects;


@RestController
@RequestMapping("/api/socks")
public class RequestController {

    private SocksService service;

    @Autowired
    public RequestController(SocksService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam String color,
                                           @RequestParam String operation,
                                           @RequestParam Integer cottonPart){
        if(Objects.equals(operation, "moreThan")){
            operation = ">";
        }
        else if(Objects.equals(operation, "lessThan")){
            operation = "<";
        }
        else if(Objects.equals(operation, "equal")){
            operation = "=";
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
        }
        if(!((0 <= cottonPart) && (cottonPart <= 100))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
        }
        Integer res = service.getNumberSocks(color, operation, cottonPart);



        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @NotNull
    private ResponseEntity<String> getStringResponseEntity(@RequestBody String body, boolean isIncome) {
        Triplet<String, Integer, Integer> parseRes;
        try{
            parseRes = ParsingService.JSONParse(body);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        SocksByType socks = new SocksByType(parseRes.getValue0(), parseRes.getValue1(), parseRes.getValue2());
        if(isIncome){
            try{
                service.income(socks);
            }
            catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
            }
        }
        else{
            try{
                service.outcome(socks);
            }
            catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("income")
    public ResponseEntity<String> incomeSocks(@RequestBody String body){
        return getStringResponseEntity(body, true);
    }

    @PostMapping("outcome")
    public  ResponseEntity<String> outcomeSocks(@RequestBody String body){
        return getStringResponseEntity(body, false);
    }
}
