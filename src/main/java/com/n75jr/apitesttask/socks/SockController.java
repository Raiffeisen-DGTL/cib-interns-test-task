package com.n75jr.apitesttask.socks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SockController {
    @Autowired
    private SockRepository sockRepository;

}
