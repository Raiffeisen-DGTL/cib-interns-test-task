package ru.testcase.accountingSocks.service;

import ru.testcase.accountingSocks.model.Socks;
import java.util.List;

public interface SocksService {

//    Socks getById(Long id);

    void save(Socks socks);

//    void delete(Long id);

    List<Socks> getAll();

//    List<Socks> getParam(Integer cottonpart);

    Socks getByColorAndCottonpart(String color, Integer cottonpart);

    List<Socks> getByParam(String color, String operation, Integer cottonpart);

    void updateSocksQuantity(String color, Integer cottonpart, Integer quantity);

    void reduceSocksQuantity(String color, Integer cottonpart, Integer quantity);

}
