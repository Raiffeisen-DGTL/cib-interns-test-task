package com.n75jr.apitesttask.socks;

import com.n75jr.apitesttask.model.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SockController {
    @Autowired
    private SockRepository sockRepository;

    // helper methods:
    private boolean isValidSock(Sock sock) {
        boolean result = true;
        if (sock.getColor() == null || sock.getCottonPart() == 0 || sock.getQuantity() == 0) {
            return false;
        }
        if (sock.getCottonPart() <= 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0) {
            return false;
        }
        return result;
    }
    //
}
