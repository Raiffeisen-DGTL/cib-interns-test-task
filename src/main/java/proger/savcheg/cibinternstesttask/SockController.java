package proger.savcheg.cibinternstesttask;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Класс-контроллер с аннотацией @RestController, который
 * обрабатывает HTTP запросы
 */
@RestController
@RequestMapping("/api")
public class SockController {

    private final SockDAO sockDAO;
    public SockController(SockDAO sockDAO) {
        this.sockDAO = sockDAO;
    }

    /**
     * Метод обрабатывающий Get-запрос с параметрами
     */
    @GetMapping("/socks")
    @ResponseBody
    ResponseEntity<Integer> getSocksParam(@RequestParam("color") String color,
                                             @RequestParam("operation") String operation,
                                             @RequestParam("cottonPart") int cottonPart) {
        return sockDAO.showWithParam(color, operation, cottonPart);
    }

    /**
     * POST запросы принимающие JSON-объект, который обрабатывается
     * аннотацией Spring @RequestBody и представляется в виде объекта
     * класса Sock
     */
    @PostMapping("/socks/income")
    ResponseEntity incomeSocks(@RequestBody Sock sock) {
        sock.setId(sock.hashCode());
        return new ResponseEntity(null, sockDAO.income(sock));
    }

    @PostMapping("/socks/outcome")
    ResponseEntity outcomeSocks(@RequestBody Sock sock) {
        sock.setId(sock.hashCode());
        return new ResponseEntity(null, sockDAO.outcome(sock));
    }
}
