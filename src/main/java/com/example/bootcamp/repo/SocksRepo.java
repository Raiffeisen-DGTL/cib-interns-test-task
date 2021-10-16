package com.example.bootcamp.repo;

import com.example.bootcamp.entity.SocksEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SocksRepo extends CrudRepository<SocksEntity,Long> {

    @Query("select s from SocksEntity s" +
            "where s.colorEntity = :color and s.cottonPartEntity = :cotton")
    Optional<SocksEntity> fingByColorAndCotton(@Param("color") String color,@Param("cotton") short cotton);
}
