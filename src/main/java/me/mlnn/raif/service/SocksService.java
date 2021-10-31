package me.mlnn.raif.service;

import me.mlnn.raif.model.SocksModel;

public interface SocksService {
    public void addSocks(SocksModel socks);
    
    public void removeSocks(SocksModel socks);
    
    public Integer getNumberOfSocks(String color, String operation, Integer cottonPart);
}
