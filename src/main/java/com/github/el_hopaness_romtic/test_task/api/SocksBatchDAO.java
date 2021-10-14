package com.github.el_hopaness_romtic.test_task.api;

import com.github.el_hopaness_romtic.test_task.model.SocksBatch;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksBatchDAO extends CrudRepository<SocksBatch, Integer> {

    List<SocksBatch> findByColorAndCottonPart(String color, int cottonPart);

    @Query("SELECT SUM(quantity) FROM socks_batch WHERE color = :color AND cotton_part < :cottonPart")
    Integer countQuantityByColorAndCottonPartLessThan(String color, int cottonPart);

    @Query("SELECT SUM(quantity) FROM socks_batch WHERE color = :color AND cotton_part > :cottonPart")
    Integer countQuantityByColorAndCottonPartMoreThan(String color, int cottonPart);

    @Modifying
    @Query("UPDATE sock SET quantity = quantity + :addition WHERE id = :id")
    int add(@Param("addition") int addition, @Param("id") int id);

    default int subtract(@Param("addition") int addition, @Param("id") int id) {
        return add(-addition, id);
    }
}
