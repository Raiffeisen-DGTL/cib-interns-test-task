package com.raiffeisen.stocktaking.repository;

import com.raiffeisen.stocktaking.model.SocksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppRepository extends JpaRepository<SocksModel, Long> {

    Optional<SocksModel> findSocksModelByColorAndCottonPart(String color, int cottonPart);

    @Query("select sum(sm.quantity) from SocksModel sm where sm.color = :color and sm.cottonPart < :cottonPart")
    Optional<Integer> findSocksModelByColorAndCottonPartLessThan(@Param("color") String color,
                                                                 @Param("cottonPart") int cottonPart);
    @Query("select sum(sm.quantity) from SocksModel sm where sm.color = :color and sm.cottonPart > :cottonPart")
    Optional<Integer> findSocksModelByColorAndCottonPartGreaterThan(@Param("color") String color,
                                                                   @Param("cottonPart") int cottonPart);
    @Query("select sm.quantity from SocksModel sm where sm.color = :color and sm.cottonPart = :cottonPart")
    Optional<Integer> findSocksModelByColorAndQuantityEquals(@Param("color") String color,
                                                             @Param("cottonPart") int cottonPart);

}