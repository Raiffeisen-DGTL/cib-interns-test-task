package com.n75jr.apitesttask.socks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SockService {
    long getSize();
    int income(Sock sock);
    int outcome(Sock sock);
    int operMoreThan(String color, int cotton_part);
    int operLessThan(String color, int cotton_part);
    int operEqual(String color, int cotton_part);
    List<Sock> testAll();
}
