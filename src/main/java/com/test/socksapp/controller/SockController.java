package com.test.socksapp.controller;

import com.test.socksapp.entity.Sock;
import com.test.socksapp.requestmodel.ComparisonOperation;
import com.test.socksapp.requestmodel.SockValueChangingRequest;
import com.test.socksapp.usecase.GetCount;
import com.test.socksapp.usecase.GetSocks;
import com.test.socksapp.usecase.RegIncome;
import com.test.socksapp.usecase.RegOutcome;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    //ToDo delete GetSocks

    private GetCount getCount;
    private RegIncome regIncome;
    private RegOutcome regOutcome;
    private GetSocks getSocks;

    public SockController(GetCount getCount, RegIncome regIncome, RegOutcome regOutcome, GetSocks getSocks) {
        this.getCount = getCount;
        this.regIncome = regIncome;
        this.regOutcome = regOutcome;
        this.getSocks = getSocks;
    }

    @PostMapping("/income")
    @ResponseStatus(HttpStatus.OK)
    public void regIncome(@RequestBody SockValueChangingRequest request) {
        regIncome.execute(request.getColor(),
                request.getCottonPart(),
                request.getQuantity()
        );
    }

    @PostMapping("/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void regOutcome(@RequestBody SockValueChangingRequest request) {
        regOutcome.execute(
                request.getColor(),
                request.getCottonPart(),
                request.getQuantity()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public int getSockCount(@RequestParam String color,
                            @RequestParam ComparisonOperation operation,
                            @RequestParam int cottonPart) {
        return getCount.execute(color, operation, cottonPart);
    }

    @GetMapping("/list")
    public List<Sock> getSocks() {
        return getSocks.execute();
    }
}
