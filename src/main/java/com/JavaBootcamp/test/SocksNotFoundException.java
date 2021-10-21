package com.JavaBootcamp.test;

public class SocksNotFoundException extends RuntimeException{
    SocksNotFoundException(Long id){
        super("Could not found socks: " + id);
    }
}
