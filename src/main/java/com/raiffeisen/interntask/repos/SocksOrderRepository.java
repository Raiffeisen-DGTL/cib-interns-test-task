package com.raiffeisen.interntask.repos;

import com.raiffeisen.interntask.classes.SocksOrder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

public interface SocksOrderRepository extends JpaRepository<SocksOrder, UUID> {
    SocksOrder findByColor_NameAndCottonPart(String color, Short cottonPart);
    boolean existsByColor_NameAndCottonPart(String color, Short cottonPart);
    List<SocksOrder> findAllByColor_Name(String color);
}
