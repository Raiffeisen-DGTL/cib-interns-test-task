package com.example.socksservice.repository;

import com.example.socksservice.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock,Long> {

    Optional<Sock> findSockByColorAndCottonPart(String color,int cottonPart);

    @Query("select sum(u.quantity) from Sock u where u.cottonPart>:cotton and u.color=:color")
    Optional<Integer> findCountMoreThan(@Param("cotton") int cotton,@Param("color")String color);

    @Query("select sum(u.quantity) from Sock u where u.cottonPart<:cotton and u.color=:color")
    Optional<Integer> findCountLessThan(@Param("cotton")int cotton,@Param("color")String color);

    @Query("select sum(u.quantity) from Sock u where u.cottonPart=:cotton and u.color=:color")
    Optional<Integer> findCountEqual(@Param("cotton")int cotton,@Param("color")String color);

}

