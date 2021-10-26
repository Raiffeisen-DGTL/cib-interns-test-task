package com.alexsimba.rafsocks.repository;

import com.alexsimba.rafsocks.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Integer countByColorAndCottonPartGreaterThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    Integer countByColorAndCottonPartLessThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    Integer countByColorAndCottonPartEquals(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    Socks getTopByColorAndCottonPart(@Param("color") String color, @Param("cottonPart") Integer cottonPart);
}
