package ru.mirraim.cib_interns_test_task.service;

import ru.mirraim.cib_interns_test_task.entity.Sock;

public interface SockService {
    Sock income(Sock sock);
    Integer findSocks(String color, String operation, int cottonPart);
    Sock outcome(Sock sock);
}
