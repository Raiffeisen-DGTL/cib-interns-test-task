package com.JavaBootcamp.test;

class SocksNegativeQuantityException extends IllegalArgumentException {

    SocksNegativeQuantityException(int quantity) {
        super(" Negative quantity of socks, take on more: " + quantity);
    }
}