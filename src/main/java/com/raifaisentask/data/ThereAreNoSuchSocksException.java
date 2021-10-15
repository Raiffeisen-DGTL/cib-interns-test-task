package com.raifaisentask.data;

public class ThereAreNoSuchSocksException extends Exception {
    public ThereAreNoSuchSocksException(Socks socks){
        super("There are no such "+socks.getColor()+" socks with "+socks.getCottonPart() +"% cotton Part in out storage");
    }
}
