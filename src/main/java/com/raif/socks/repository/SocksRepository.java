package com.raif.socks.repository;

import com.raif.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, SocksEntity.PrimaryKey>, JpaSpecificationExecutor<SocksEntity> {

    Optional<SocksEntity> findOneByColorAndCottonPart(String color, int cottonPart);

}
