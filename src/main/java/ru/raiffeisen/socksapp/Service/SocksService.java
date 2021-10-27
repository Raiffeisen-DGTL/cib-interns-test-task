package ru.raiffeisen.socksapp.Service;

import ru.raiffeisen.socksapp.Model.Socks;

import javax.validation.constraints.NotNull;

public interface SocksService {
    Long getQuantityOfSocks(@NotNull String color, @NotNull String operation, int cottonPart);
    void addSocks(Socks socks);
    void removeSocks(Socks socks);
}
