package com.example.TaskSocks.Repository;

import com.example.TaskSocks.Model.Socks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SocksRepository extends CrudRepository<Socks,Long>
{
    @Query(value = "SELECT sum(quantity) FROM Socks where color=:c and cotton_part=:cp",nativeQuery = true)
    int findEqualSocks(@Param("c") String color,@Param("cp") int cotton_part);

    @Query(value = "SELECT sum(quantity) FROM Socks where color=:c and cotton_part>:cp",nativeQuery = true)
    int findMoreSocks(@Param("c") String color,@Param("cp") int cotton_part);

    @Query(value = "SELECT sum(quantity) FROM Socks where color=:c and cotton_part<:cp",nativeQuery = true)
    int findLessSocks(@Param("c") String color,@Param("cp") int cotton_part);

    @Query(value = "SELECT * FROM Socks where color=:c and cotton_part=:cp",nativeQuery = true)
    Socks findSocks(@Param("c") String color,@Param("cp") int cotton_part);
}
