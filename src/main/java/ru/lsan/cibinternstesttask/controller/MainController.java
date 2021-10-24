package ru.lsan.cibinternstesttask.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @PostMapping
    public String hello(){
        return "hello!";
    }

}
