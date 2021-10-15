package com.example.springsocks.resource;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import com.example.springsocks.domain.Socks;
import com.example.springsocks.service.SocksService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * SocksResourceImpl.
 *
 * @author Alexander_Tupchin
 */
@RestController
@AllArgsConstructor
public class SocksResourceImpl implements SocksResource {

    private final SocksService socksService;

    @Override
    public ResponseEntity<Long> getCountSocks(@RequestParam(value = "color") String color,
                                              @RequestParam(value = "operation") String operation,
                                              @RequestParam(value = "cottonPart") Integer cottonPart) {
        Long result = socksService.getCountSocks(color, operation, cottonPart);
        return ok(result);
    }

    @Override
    public ResponseEntity<Void> addSocks(@RequestBody Socks socks) {
        socksService.addSocks(socks);
        return noContent().build();
    }

    @Override
    public ResponseEntity<Void> reduceSocks(@RequestBody Socks socks) {
        socksService.reduceSocks(socks);
        return noContent().build();
    }

}
