package ru.mirraim.cib_interns_test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirraim.cib_interns_test_task.entity.Sock;
import ru.mirraim.cib_interns_test_task.exception_handling.NoSuchSocksException;
import ru.mirraim.cib_interns_test_task.service.SockService;

@RestController
@RequestMapping("/api")
public class SockController {

    private SockService service;

    @Autowired
    public SockController(SockService service) {
        this.service = service;
    }

    @PostMapping("/socks/income")
    public Sock income(@RequestBody Sock sock) {
        if (sock.getQuantity() < 0) {
            throw new NoSuchSocksException("некорректно введено количество");
        }
        System.out.println(sock);
        if (sock.getCottonPart() < 0 || sock.getCottonPart() > 100) {
            throw new NoSuchSocksException(
                    "содержание хлопка должно быть в пределах от 0 до 100"
            );
        }
        sock = service.income(sock);
        if (sock == null) {
            throw new IllegalStateException("не удалось произвести добавление");
        }
        return sock;
    }

    @PostMapping("/socks/outcome")
    public Sock outcome(@RequestBody Sock sock) {
        if (sock.getQuantity() < 0) {
            throw new NoSuchSocksException("некорректно введено количество");
        }
        sock = service.outcome(sock);
        return sock;
    }

    @GetMapping("/socks")
    public Integer find(@RequestParam(name = "color") String color,
                            @RequestParam("operation") String operation,
                            @RequestParam("cottonPart") int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new NoSuchSocksException(
                    "содержание хлопка должно быть в пределах от 0 до 100"
            );
        }
        return service.findSocks(color, operation, cottonPart);
    }
}
