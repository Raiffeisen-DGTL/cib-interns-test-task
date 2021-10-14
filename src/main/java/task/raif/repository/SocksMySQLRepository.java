package task.raif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import task.raif.model.SocksStorage;

import java.util.Optional;

@Repository
public interface SocksMySQLRepository extends JpaRepository<SocksStorage, Long> {

    public Optional<SocksStorage> getByColorAndCottonPart(String color, int cottonPart);

    @Query("select sum(quantity) from SocksStorage "
            + "where (:color = color) and (:cottonPart > cottonPart)")
    public Optional<Integer> getQuantityByColorAndCottonPartLessThan(String color, int cottonPart);

    @Query("select sum(quantity) from SocksStorage "
            + "where (:color = color) and (:cottonPart < cottonPart)")
    public Optional<Integer> getQuantityByColorAndCottonPartMoreThan(String color, int cottonPart);

}
