package com.example.TaskSocks.Controller;

import com.example.TaskSocks.Exeptions.ErrorOperationExeption;
import com.example.TaskSocks.Model.Socks;
import com.example.TaskSocks.Service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class Main
{
    @Autowired
    SocksService socksService;
    @GetMapping
    public ResponseEntity mainPage(@RequestParam("color") String color,
                                   @RequestParam("cottonPart") int cottonPart,
                                   @RequestParam("operation") String operation)
    {
        int result = 0;
        try { result = socksService.result(color, cottonPart, operation);
        } catch (ErrorOperationExeption errorOperationExeption) {
            return ResponseEntity.badRequest().body(errorOperationExeption.getMessage());
        }
        return ResponseEntity.ok().body(result);
    }
    /**
     * В Post запросе параметры передаются в теле запроса (json объект) класса Socks
     * */
    @PostMapping("/income")
    public ResponseEntity add(@RequestBody Socks socks)
    {
        try { return ResponseEntity.ok().body(socksService.add(socks));
        }
        catch (ErrorOperationExeption errorOperationExeption) {
           return ResponseEntity.badRequest().body(errorOperationExeption.getMessage());
        }
    }
    @PostMapping("/outcome")
    public ResponseEntity remove(@RequestBody Socks socks)
    {
        try { return ResponseEntity.ok().body(socksService.remove(socks));
        }
        catch (ErrorOperationExeption errorOperationExeption) {
            return ResponseEntity.badRequest().body(errorOperationExeption.getMessage());
        }
    }

}
