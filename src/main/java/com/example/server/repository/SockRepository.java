package com.example.server.repository;

import com.example.server.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SockRepository extends JpaRepository<Sock, Integer>
{
}
