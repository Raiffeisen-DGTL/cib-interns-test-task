package com.example.testsocks.repository;

import com.example.testsocks.model.Socks;
import com.example.testsocks.model.SocksPK;

import java.util.List;

public interface SocksRepository {
    void income(Socks socks);
    boolean outcome(Socks socks);
    int countMoreThan(SocksPK socksPK);
    int countLessThan(SocksPK socksPK);
    int getQuantity(SocksPK socksPK);
}
