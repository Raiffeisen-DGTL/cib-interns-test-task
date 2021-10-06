package com.example.testtask.store.repositories;

import com.example.testtask.store.entities.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.stream.Stream;

public interface SocksRepository extends JpaRepository<SocksEntity,Long> {

    Stream<SocksEntity> streamAllByCottonPart(Double cottonPart);
}
