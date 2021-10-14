package ru.raiffeisen.dgtl.cib.intern.task.service;

import ru.raiffeisen.dgtl.cib.intern.task.Operation;
import ru.raiffeisen.dgtl.cib.intern.task.entity.Socks;
import ru.raiffeisen.dgtl.cib.intern.task.entity.SocksId;

public interface SocksService {
    Long quantity(SocksId socksId, Operation operation);

    void income(Socks socks);

    void outcome(Socks socks);
}
