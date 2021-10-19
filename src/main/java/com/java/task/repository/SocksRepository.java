package com.java.task.repository;

import com.java.task.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Socks findByColorIdAndCottonPart(int color, int cottonPart);

    List<Socks> findByColorIdAndCottonPartGreaterThan(int color, int greaterThan);

    List<Socks> findByColorIdAndCottonPartLessThan(int color, int lessThan);

    List<Socks> findByColorIdAndCottonPartEquals(int color, int equals);
}
