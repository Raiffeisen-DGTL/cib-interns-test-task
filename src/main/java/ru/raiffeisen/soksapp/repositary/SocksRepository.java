package ru.raiffeisen.soksapp.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.raiffeisen.soksapp.entity.SockEntity;

import java.util.List;

public interface SocksRepository extends JpaRepository<SockEntity, Long> {

    @Query("select sock from SockEntity sock where sock.color = :color AND sock.cottonPart = :cottonPart")
    SockEntity findSocks(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("select sock from SockEntity sock where sock.color = :color AND sock.cottonPart > :cottonPart")
    List<SockEntity> findSocksCottonPartMoreThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("select sock from SockEntity sock where sock.color = :color AND sock.cottonPart < :cottonPart")
    List<SockEntity> findSocksCottonPartLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);
}
