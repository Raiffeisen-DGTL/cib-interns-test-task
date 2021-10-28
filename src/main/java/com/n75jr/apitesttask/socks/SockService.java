package com.n75jr.apitesttask.socks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SockService {
    long getSize();
    void income(Sock sock);
    void outcome(Sock sock);
    void outcomeWithoutId(String color, int cotton_part);
    int operMoreThan(String color, int cotton_part);
    int operLessThan(String color, int cotton_part);
    int operEqual(String color, int cotton_part);
    // test
    List<Sock> testAll();
}
