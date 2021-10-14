package ru.raiffeisen.shopwarehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.shopwarehouse.entity.Socks;
import ru.raiffeisen.shopwarehouse.service.SocksService;
import ru.raiffeisen.shopwarehouse.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static ru.raiffeisen.shopwarehouse.utils.DataBaseCheckerUtil.isDataBaseConnected;

@RestController
@RequestMapping("/api")
public class SocksController {

    private final SocksService socksService;
    private SimpleGrantedAuthority requiredRole;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
        requiredRole = new SimpleGrantedAuthority("STOREKEEPER");
    }

    @PostMapping("/socks/income")
    public ResponseEntity<Socks> income(@RequestBody Socks socks) throws SQLException {
        Collection<? extends GrantedAuthority> userRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (!isDataBaseConnected()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (socks.getQuantity() <= 0
                || socks.getColor().equals("")
                || socks.getCottonPart() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(userRoles.contains(requiredRole)) {
            Socks localSocks = socksService.get(socks);
            if (localSocks != null) {
                localSocks.setQuantity(localSocks.getQuantity() + socks.getQuantity());
                socksService.update(localSocks, localSocks.getSocksId());
                return new ResponseEntity<>(socks, HttpStatus.OK);
            } else {
                socksService.create(socks);
                return new ResponseEntity<>(socks, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/socks/outcome")
    public ResponseEntity<Socks> outcome(@RequestBody Socks socks) throws SQLException {
        Collection<? extends GrantedAuthority> userRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if(!isDataBaseConnected()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(socks.getQuantity() <= 0
                ||socks.getColor().equals("")
                || socks.getCottonPart() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(userRoles.contains(requiredRole)) {
            Socks localSocks = socksService.get(socks);
            if(!localSocks.equals(null)) {
                socks.setSocksId(localSocks.getSocksId());
                socks.setQuantity(localSocks.getQuantity() - socks.getQuantity());
                socksService.update(socks, socks.getSocksId());
                return new ResponseEntity<>(socks, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/socks/getSocks")
    public ResponseEntity<List<Socks>> getSocks(Socks socks) throws SQLException {
        if(!isDataBaseConnected()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(socks.getColor().equals("")
                || socks.getCottonPart() < 0
                || socks.getOperation().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            List<Socks> socksList = new ArrayList<>(socksService.getAll());
            if(!socksList.equals(null)) {
                if(socks.getOperation().equals("moreThan")) {
                    socksList.removeIf(s -> s.getCottonPart() <= socks.getCottonPart()
                            || !Objects.equals(s.getColor(), socks.getColor()));
                } else if(socks.getOperation().equals("lessThan")) {
                    socksList.removeIf(s -> s.getCottonPart() >= socks.getCottonPart()
                            || !Objects.equals(s.getColor(), socks.getColor()));
                } else {
                    socksList.removeIf(s -> !Objects.equals(s.getColor(), socks.getColor()));
                }
                return new ResponseEntity<>(socksList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

    }
}
