package com.n75jr.apitesttask.socks;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SockService {
    int income(Sock sock);
    int outcome(Sock sock);
    Long operMoreThan(String color, int cotton_part) throws NullPointerException;
    Long operLessThan(String color, int cotton_part) throws NullPointerException;
    Long operEqual(String color, int cotton_part) throws NullPointerException;
    List<Sock> getAll();
}
