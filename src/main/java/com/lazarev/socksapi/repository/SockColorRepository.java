package com.lazarev.socksapi.repository;

import com.lazarev.socksapi.entity.SockColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SockColorRepository extends JpaRepository<SockColor, Long> {
    Optional<SockColor> findByColor(String color);
}
