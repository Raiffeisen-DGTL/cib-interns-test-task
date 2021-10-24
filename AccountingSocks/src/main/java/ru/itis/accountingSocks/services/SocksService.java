package ru.itis.accountingSocks.services;

import ru.itis.accountingSocks.dto.SocksDto;
import ru.itis.accountingSocks.forms.SocksForm;

public interface SocksService {
    SocksDto addSocks(SocksForm socks);

    SocksDto reduceSocks(SocksForm socks);

    int getTotalQuantitySocks(String color, String operation, int cottonPart);
}
