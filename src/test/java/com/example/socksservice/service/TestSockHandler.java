package com.example.socksservice.service;

import com.example.socksservice.model.Sock;

public class TestSockHandler {
    public static Sock getSock(){
        return  new Sock("red",12,5);
    }

    public static Sock getSecondSockWithLargeNumberOfPairs(){
        return  new Sock("red",12,23);
    }

    public static Sock getSockWithIncorrectCottonPart(){
        return  new Sock("white",-2,11);
    }

    public static Sock getSockWithIncorrectQuantity(){
        return  new Sock("white",2,-3);
    }
}
