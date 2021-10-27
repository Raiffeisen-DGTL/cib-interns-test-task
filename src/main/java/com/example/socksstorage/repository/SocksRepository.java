package com.example.socksstorage.repository;

import com.example.socksstorage.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
}
