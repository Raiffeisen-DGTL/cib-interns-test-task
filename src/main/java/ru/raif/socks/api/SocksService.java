package ru.raif.socks.api;

import ru.raif.socks.model.SocksModel;

public interface SocksService {
    void income(SocksModel socksModel);
    boolean outcome(SocksModel socksModel);
    int findSocksByParameters(String color, String operation, int cottonPart);
}
