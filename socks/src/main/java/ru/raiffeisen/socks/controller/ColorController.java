package ru.raiffeisen.socks.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.socks.service.ColorService;

import java.util.Map;

@RestController
@RequestMapping("/api/color")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping
    public void addColor(@RequestBody Map<String, String> color) {
        colorService.addColor(color.get("color"));
    }
}
