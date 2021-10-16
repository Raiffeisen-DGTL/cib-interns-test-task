package com.example.accountingofsocks.repository;

import com.example.accountingofsocks.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SocksDao extends JpaRepository<Socks,Long> {

    List<Socks> findAllByColorAndCottonPartGreaterThan(String color,byte cottonPart);

    List<Socks> findAllByColorAndCottonPartEquals(String color,byte cottonPart);

    List<Socks> findAllByColorAndCottonPartIsLessThan(String color,byte cottonPart);
}
