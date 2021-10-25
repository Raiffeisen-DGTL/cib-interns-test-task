package com.raiffeisentesttask.accountingofsocks.aos.service;

import com.raiffeisentesttask.accountingofsocks.aos.entity.Socks;

public interface SocksService {

    void saveOrUpdateSocks(Socks userSocks);

    boolean deleteSocks(Socks userSocks);

    int findTheNumberOfMatchingSocks(String color, String operation, int cottonPart);




}
