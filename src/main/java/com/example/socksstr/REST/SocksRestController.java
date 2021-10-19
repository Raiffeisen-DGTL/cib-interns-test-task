package com.example.socksstr.REST;


import com.example.socksstr.Model.Socks;
import com.example.socksstr.Service.SocksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/socks/")
@AllArgsConstructor
public class SocksRestController {

    private SocksService socksService;

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Socks> saveSocks(@RequestBody Socks socks) {
        HttpHeaders headers = new HttpHeaders();

        if (socks == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.socksService.save(socks);
        return new ResponseEntity<>(socks, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSocks(@PathVariable Long id) {
        Socks socks = this.socksService.getById(id);

        if (socks == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.socksService.delete(id,socks);
        return new ResponseEntity(HttpStatus.NO_CONTENT, HttpStatus.valueOf("Вещь удалена"));
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Socks>> getAllSocks() {
        List<Socks> socksList = this.socksService.getAll();

        if (socksList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(socksList, HttpStatus.OK);
    }


}
