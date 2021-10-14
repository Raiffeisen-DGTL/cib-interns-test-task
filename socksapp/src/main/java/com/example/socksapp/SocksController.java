package com.example.socksapp;


import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * Контроллер для обработки действия пользователя,
 * связанных с учетом носков на складе магазина
 */
@Controller
@Validated
public class SocksController {

    private final SockRepository sockRepository;

    public SocksController(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    /**
     * Регистрация прихода носков на склад
     * @param socks носки
     * @return http статус
     */
    @PostMapping(value = "/api/socks/income",produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> registerSocks(@Valid @RequestBody Socks socks){
        Optional<Socks> existsSocks = sockRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        // если носки с таким цветом и кол-вом хлопка есть, то увеличиваем количество
        if(existsSocks.isPresent()){
            existsSocks.get().setQuantity(existsSocks.get().getQuantity() + socks.getQuantity());
            sockRepository.save(existsSocks.get());
        }
        // иначе просто добавляем носки в базу
        else {
            sockRepository.save(socks);
        }
        return new ResponseEntity<>("Удалось добавить приход",HttpStatus.OK);
    }

    /**
     * Регистрирует отпуск носков со склада
     * @param socks носки
     * @return http статус
     */
    @PostMapping(value = "/api/socks/outcome",produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> outcomeSocks(@Valid @RequestBody Socks socks){
        Optional<Socks> existsSocks = sockRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if(existsSocks.isPresent() && existsSocks.get().getQuantity() >= socks.getQuantity()){
            existsSocks.get().setQuantity(existsSocks.get().getQuantity() - socks.getQuantity());
            sockRepository.save(existsSocks.get());
            return new ResponseEntity<>("Удалось отпустить носки", HttpStatus.OK);
        }
        return new ResponseEntity<>("Требуемых носков нет в системе или указанное кол-во носков больше имеющегося",
                HttpStatus.BAD_REQUEST);
        }

    /**
     * Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
     */
    @GetMapping("/api/socks")
    public ResponseEntity<String> getSocks(@RequestParam("color") @NotBlank String color,
                                           @RequestParam("operation") @NotNull Comparison operation,
                                           @RequestParam("cottonPart") @Min(0) @Max(100) Integer cottonPart){

            if(operation == Comparison.equal){
                return new ResponseEntity<>(String.valueOf(sockRepository.findByColorAndCottonPart(color, cottonPart)
                        .map(Socks::getQuantity).orElse(0L)), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(String.valueOf(getAmount(operation,color, cottonPart)),HttpStatus.OK);
            }
    }

    /** Получить количество носков в зависимости от операции moreThan или lessThan*/
    private long getAmount(Comparison comparison, String color, Integer cottonPart){
        Optional<List<Socks>> socks = comparison == Comparison.moreThan ?
                sockRepository.findByColorAndCottonPartGreaterThan(color, cottonPart) :
                sockRepository.findByColorAndCottonPartLessThan(color, cottonPart);
        return socks.isPresent() ? socks.get().stream()
                .map(Socks::getQuantity).reduce(0L, Long::sum) : 0;
    }
}
