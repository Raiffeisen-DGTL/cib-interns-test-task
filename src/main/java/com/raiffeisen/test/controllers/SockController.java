package com.raiffeisen.test.controllers;

import com.raiffeisen.test.dtos.SockBatchDto;
import com.raiffeisen.test.entities.SockBatch;
import com.raiffeisen.test.services.SockBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/socks")
public class SockController {

    @Autowired
    private SockBatchService sockService;

    @GetMapping()
    public ResponseEntity<List<SockBatchDto>> findByColorAndOperationAndCottonPart(@RequestParam(value = "color", required = false, defaultValue = "all") String color,
                                                                                   @RequestParam(value = "operation", required = false, defaultValue = "greaterThan") String operation,
                                                                                   @RequestParam(value = "cottonPart", required = false, defaultValue = "0") Integer cottonPart) {
        Arrays.binarySearch()
        return new ResponseEntity<>(sockService.findByColorAndOperationAndCottonPart(color, operation, cottonPart), HttpStatus.OK);
    }

   @PostMapping("/income/color={color}&cottonPart={colorPart}&quantity={quantity}")
    public ResponseEntity<SockBatch> save(@PathVariable String color, @PathVariable int colorPart, @PathVariable int quantity) {
        return ResponseEntity.ok(sockService.save(color, colorPart, quantity));
   }

   @PostMapping("/outcome/color={color}&cottonPart={cottonPart}&quantity={quantity}")
    public ResponseEntity<?> delete(@PathVariable String color, @PathVariable int cottonPart, @PathVariable int quantity) {
        sockService.delete(color, cottonPart, quantity);
        return ResponseEntity.ok().build();
   }

}
