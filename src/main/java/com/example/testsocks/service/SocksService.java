package com.example.testsocks.service;

import com.example.testsocks.model.Socks;
import com.example.testsocks.model.SocksPK;

import java.util.List;

public interface SocksService {
    void income(Socks socks);
    boolean outcome(Socks socks);
    int countSocks(SocksPK socksPK, String operation);
}
