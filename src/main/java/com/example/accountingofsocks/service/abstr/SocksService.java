package com.example.accountingofsocks.service.abstr;

import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
import com.example.accountingofsocks.model.Operation;
import com.example.accountingofsocks.model.Socks;
import java.util.List;
import java.util.OptionalInt;

public interface SocksService {
    Socks income(Socks socks);

    void outcome(Socks socks) throws QuantitySocksOutOfBoundsException;

    List<Socks> findByParameters(String color, Operation operation, byte cottonPart);

    OptionalInt getNumberSocksByParameters(String color, Operation operation, byte cottonPart);

}
