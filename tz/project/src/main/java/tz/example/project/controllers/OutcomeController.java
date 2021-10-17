package tz.example.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tz.example.project.models.Socks;
import tz.example.project.services.OutcomeService;

@Controller
public class OutcomeController {

    @Autowired
    private final OutcomeService outcomeService;

    @Autowired
    public OutcomeController(OutcomeService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/outcome")
    public void outcome(@RequestBody Socks socks) {
        outcomeService.outcome(socks);
    }
}
