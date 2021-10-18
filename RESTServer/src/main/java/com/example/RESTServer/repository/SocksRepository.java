package com.example.RESTServer.repository;

import com.example.RESTServer.models.Socks;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SocksRepository extends JpaRepository<Socks, Long> {

}
