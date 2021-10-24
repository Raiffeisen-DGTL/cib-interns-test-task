package com.raiffeisen.test.repositories;

import com.raiffeisen.test.entities.SockBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ISockBatchRepository extends JpaRepository<SockBatch, Long> {

    List<SockBatch> findAllByCottonPartGreaterThan(int cottonPart);

    List<SockBatch> findAllByCottonPartLessThan(int cottonPart);

    List<SockBatch> findAllByCottonPartEquals(int cottonPart);

    Optional<SockBatch> findByColorAndCottonPart(String color, int cottonPart);

    List<SockBatch> findByColorAndCottonPartLessThan(String color, int cottonPart);

    Optional<SockBatch> findByColorAndCottonPartEquals(String color, int cottonPart);

    List<SockBatch> findByColorAndCottonPartGreaterThan(String color, int cottonPart);

    @Transactional
    @Modifying
    @Query("update SockBatch s set s.quantity = ?1 where s.color = ?2 and s.cottonPart = ?3")
    void updateSockQuantity(int newQuantity, String color, int cottonPart);

}
