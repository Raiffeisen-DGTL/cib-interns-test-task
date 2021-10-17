package com.example.bootcamp.repo;

import com.example.bootcamp.entity.SocksEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SocksRepo extends CrudRepository<SocksEntity, Long> {

    @Query("select s from SocksEntity s" +
            " where s.colorEntity = :color and s.cottonPartEntity = :cotton")
    Optional<SocksEntity> fingByColorAndCotton(@Param("color") String color, @Param("cotton") int cotton);

    @Query("select s from SocksEntity s" +
            " where s.colorEntity = :color")
    List<SocksEntity> findByColor(@Param("color") String color);

    @Query("select coalesce(sum(s.quantityEntity),0) from SocksEntity s" +
            " where s.colorEntity = :color and s.cottonPartEntity > :cotton")
    int findSocksMoreThan(@Param("color") String color, @Param("cotton") int cotton);

    @Query("select coalesce(sum(s.quantityEntity),0) from SocksEntity s" +
            " where s.colorEntity = :color and s.cottonPartEntity = :cotton")
    int findSocksEqual(@Param("color") String color, @Param("cotton") int cotton);

    @Query("select coalesce(sum(s.quantityEntity),0) from SocksEntity s" +
            " where s.colorEntity = :color and s.cottonPartEntity < :cotton")
    int findSocksLessThan(@Param("color") String color, @Param("cotton") int cotton);
}
