package raif.test.socks.dao;

import org.springframework.stereotype.Repository;
import raif.test.socks.model.Sock;
import org.springframework.data.repository.CrudRepository;
import raif.test.socks.model.SockPK;

import java.awt.*;
import java.util.Optional;

@Repository
public interface SocksRepository extends CrudRepository<Sock, SockPK> {

    public Iterable<Sock> findAllByColorAndCottonPartGreaterThan(String color, Byte cottonPart);
    public Iterable<Sock> findAllByColorAndCottonPartLessThan(String color, Byte cottonPart);
    Optional<Sock> findByColorAndCottonPart(String color, Byte cottonPart);

}