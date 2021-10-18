package com.intern.sock.repository;
import com.intern.sock.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface Repository extends JpaRepository<Sock,Long> {
    List<Sock> getSocksByColorAndCottonPartLessThan(String color, Long cottonPart);
    Optional<Sock> getSockByColorAndCottonPartEquals(String color, Long cottonPart);
    List<Sock> getSockByColorAndCottonPartIsGreaterThan(String color, Long cottonPart);
}
