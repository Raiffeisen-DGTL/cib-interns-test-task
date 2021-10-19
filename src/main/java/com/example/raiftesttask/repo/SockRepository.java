package com.example.raiftesttask.repo;

import com.example.raiftesttask.domain.Color;
import com.example.raiftesttask.domain.Sock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface SockRepository extends CrudRepository<Sock,Long> {

     List<Sock> findByColorAndCottonPartAfter(Color color,Integer cottonPart);
     List<Sock> findByColorAndCottonPartBefore(Color color, Integer cottonPart);
     Optional<Sock> findFirstByColorAndCottonPartEquals(Color color, Integer cottonPart);
     List<Sock> findAll();
}
