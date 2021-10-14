package alaev.dev.raiffeisentesttask.repository;

import alaev.dev.raiffeisentesttask.domain.Sock;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SockRepository extends CrudRepository<Sock, Long> {

  Optional<Sock> findSockByColorAndCottonPart(String color, Integer cottonPart);

  Optional<Sock> findSockByColorAndCottonPartAndQuantityIsGreaterThanEqual(String color,
                                                                           Integer cottonPart,
                                                                           Integer quantity);

  @Query("select sum(s.quantity) "
      + "from socks s "
      + "where s.color = ?1 and s.cottonPart = ?2"
      + " group by s.color")
  Long getTotalNumberByColorAndCottonPartEqual(String color, Integer cottonPart);

  @Query("select sum(s.quantity) "
      + "from socks s "
      + "where s.color = ?1 and s.cottonPart > ?2"
      + " group by s.color")
  Long getTotalNumberByColorAndCottonPartMoreThan(String color, Integer cottonPart);

  @Query("select sum(s.quantity) "
      + "from socks s "
      + "where s.color = ?1 and s.cottonPart < ?2"
      + " group by s.color")
  Long getTotalNumberByColorAndCottonPartLessThan(String color, Integer cottonPart);
}