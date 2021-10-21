package com.JavaBootcamp.test;

public class TheseSocksNotFoundException extends IndexOutOfBoundsException{
    TheseSocksNotFoundException() {
        super("Socks with these parameters not found");
    }
}
