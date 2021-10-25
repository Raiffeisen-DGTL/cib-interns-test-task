package appAmirSalyakhov.raiffeisenTask.repository;

import appAmirSalyakhov.raiffeisenTask.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query(value = "SELECT id, color, cotton_part, available_quantity FROM socks_warehouse WHERE color = ?1 AND cotton_part > ?2", nativeQuery = true)
    List<Socks> findSocksByColorAndCottonPartIsGreaterThan(String color, int cottonParts);

    @Query(value = "SELECT id, color, cotton_part, available_quantity FROM socks_warehouse WHERE color = ?1 AND cotton_part < ?2", nativeQuery = true)
    List<Socks> findSocksByColorAndCottonPartIsLessThan(String color, int cottonParts);

    @Query(value = "SELECT id, color, cotton_part, available_quantity FROM socks_warehouse  WHERE color = ?1 AND cotton_part = ?2", nativeQuery = true)
    List<Socks> findSocksByColorAndCottonPartEquals(String color, int cottonPart);

    @Query(value = "SELECT id, color, cotton_part, available_quantity FROM socks_warehouse  WHERE color = ?1 AND cotton_part = ?2", nativeQuery = true)
    Socks selectSocksByColorAndCottonPartEquals(String color, int cottonPart);
}
