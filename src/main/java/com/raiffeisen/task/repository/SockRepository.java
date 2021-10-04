package com.raiffeisen.task.repository;

import com.raiffeisen.task.domain.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SockRepository extends JpaRepository<Sock, Integer> {


}
