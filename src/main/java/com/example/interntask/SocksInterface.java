package com.example.interntask;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocksInterface extends JpaRepository<SocksByType, Integer> {
    List<SocksByType> findAllByColorAndCottonPartGreaterThan(String color, Integer cottonPrt);

    List<SocksByType> findAllByColorAndCottonPartEquals(String color, Integer cottonPart);

    List<SocksByType> findAllByColorAndCottonPartIsLessThan(String color, Integer cottonPart);

    @NotNull List<SocksByType> findAll();


}
