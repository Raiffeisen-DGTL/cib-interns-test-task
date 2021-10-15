package com.home.sock.repository;

import com.home.sock.models.Composite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompositeRepository extends JpaRepository<Composite, Long> {
    Optional<Composite> findOneByCottonPart(int cotton_part);
}
