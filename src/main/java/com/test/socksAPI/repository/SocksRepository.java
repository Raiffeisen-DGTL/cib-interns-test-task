package com.test.socksAPI.repository;

import com.test.socksAPI.accessingdata.Socks;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SocksRepository extends CrudRepository<Socks, Integer> {
    List<Socks> findByColorAndCottonPart(String color, byte cottonPart);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Socks (color,cotton_part,quantity) VALUES (:color,:cotton_part,:quantity) ON CONFLICT (color,cotton_part) DO UPDATE SET quantity = Socks.quantity + :quantity", nativeQuery = true)
    void updateQuantity(@Param("color") String color, @Param("cotton_part") byte cottonPart, @Param("quantity") int quantity);





    @Query(value = "SELECT COALESCE(sum(s.quantity),0) FROM Socks s WHERE s.color=:color and cotton_part > :cotton_part", nativeQuery = true)
    long countSocksMoreThan(@Param("color") String color, @Param("cotton_part") byte cottonPart);

    @Query(value = "SELECT COALESCE(sum(s.quantity),0) FROM Socks s WHERE s.color=:color and cotton_part < :cotton_part", nativeQuery = true)
    long countSocksLessThan(@Param("color") String color, @Param("cotton_part") byte cottonPart);

    @Query(value = "SELECT COALESCE(sum(s.quantity),0) FROM Socks s WHERE s.color=:color and cotton_part = :cotton_part", nativeQuery = true)
    long countSocksEqual(@Param("color") String color, @Param("cotton_part") byte cottonPart);


}
