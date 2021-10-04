package com.raiffeisen.task.repository;

import com.raiffeisen.task.domain.Sock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Integer> {

    Optional<Sock> findSockByColorAndCottonPart(String color, Integer cottonPart);


}
