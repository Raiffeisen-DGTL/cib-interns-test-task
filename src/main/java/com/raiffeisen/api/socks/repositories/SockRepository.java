package com.raiffeisen.api.socks.repositories;

import com.raiffeisen.api.socks.entities.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface SockRepository extends JpaRepository <Sock, Integer> {
   @Nullable
    //поиск по конкретному значению процента хлопка и цвета
    public Sock findByColorAndCottonPart(String color, Integer cottonPart);

   @Nullable
    //поиск по конкретному значению цвета и процент хлопка меньше указанного
    public Sock findByColorAndCottonPartLessThan(String color, Integer cottonPart);

    @Nullable
    //поиск по конкретному значению цвета и процент хлопка больше указанного
    public Sock findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

    public Sock findByColorAndCottonPartAndQuantityGreaterThanEqual
            (String color, Integer cottonPart, Integer quantity);

}
