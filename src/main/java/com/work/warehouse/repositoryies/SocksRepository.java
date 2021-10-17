package com.work.warehouse.repositoryies;

import com.work.warehouse.entities.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    Optional<Socks> findSocksById(String id);
    List<Socks> findSocksByColor(String color);
}
