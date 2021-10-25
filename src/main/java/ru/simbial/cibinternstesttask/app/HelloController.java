package ru.simbial.cibinternstesttask.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping(produces = "application/json")
    public String getHelloMsg() {
        return
                "Available endpoints: " +
                        "\n GET | /api/socks        |required params: color, operation(moreThan/lessThan/equal), cottonPart" +
                        "\n POST| /api/socks/income |required request body attrs: color, cottonPart, quantity" +
                        "\n POST| /api/socks/outcome|required request body attrs: color, cottonPart, quantity";
    }
}
