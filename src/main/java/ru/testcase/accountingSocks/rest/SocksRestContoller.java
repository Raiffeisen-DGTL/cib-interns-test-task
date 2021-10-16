package ru.testcase.accountingSocks.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testcase.accountingSocks.model.Socks;
import ru.testcase.accountingSocks.service.SocksService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksRestContoller {

    @Autowired
    private SocksService socksService;

    @RequestMapping(value = "/income", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Socks> saveSocks(@RequestBody @Valid Socks socks) {
        HttpHeaders headers = new HttpHeaders();
        if(socksService.getByColorAndCottonpart(socks.getColor(), socks.getCottonPart())==null){
            this.socksService.save(socks);
        } else {
            int addQuantity = socksService.getByColorAndCottonpart(socks.getColor(), socks.getCottonPart()).getQuantity();
            socksService.updateSocksQuantity(socks.getColor(), socks.getCottonPart(), socks.getQuantity()+addQuantity);
        }

        if (socks == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(socks, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/outcome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Socks> reduceSocks(@RequestBody @Valid Socks socks) {
        HttpHeaders headers = new HttpHeaders();
        if(socksService.getByColorAndCottonpart(socks.getColor(), socks.getCottonPart())==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            int reduceQuantity = socks.getQuantity();
            System.out.println("reduceQuantity: "+reduceQuantity);
            socksService.reduceSocksQuantity(socks.getColor(), socks.getCottonPart(), socks.getQuantity());
        }

        if (socks == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(socks, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Socks>> getAllSocks() {
        List<Socks> Socks = this.socksService.getAll();

        if (Socks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Socks, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Socks>> getTest(@RequestParam("color") String color,
                                               @RequestParam("operation") String operation,
                                               @RequestParam("cottonPart") Integer cottonpart) {
        List<Socks> Socks = this.socksService.getByParam(color,operation, cottonpart);

        if (Socks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Socks, HttpStatus.OK);
    }

}
