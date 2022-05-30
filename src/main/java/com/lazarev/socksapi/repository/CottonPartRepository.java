package com.lazarev.socksapi.repository;

import com.lazarev.socksapi.entity.CottonPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CottonPartRepository extends JpaRepository<CottonPart, Long> {

    Optional<CottonPart> findByCottonPart(Integer cottonPart);
}
